package com.example.jinbailiang.photocropupload;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jinbailiang.demos_jinbai.R;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoCropUploadActivity extends Activity implements View.OnClickListener {

    private static final int TO_CAMERA_PAGE = 1;
    private static final int TO_ALBUM_PAGE = 2;
    private static final int CAMERA_TO_CROP_PAGE = 3;
    private static final int ALBUM_TO_CROP_PAGE = 4;
    private TextView tvUpdateImage;
    private ImageView currentImage;
    private File sdcardTempFile;
    private String path = "/mnt/sdcard/Mydemo/showImage/"; //设定查询目录
    private File[] files;
    private HorizontalScrollView horizontalScrollView;
    private InputStream in;
    private Uri imageUri;
    private Uri imageCropUri;
    private File cropFilePath;
    private LinearLayout ll_imageShow;
    private String fileName;
    private    float downX = 0;
    private   float downY = 0;
    private  float upX = 0;
    private  float upY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_crop_upload);
        initViews();
        showImageslist();
        setViewsItemClick();
    }

    private void setViewsItemClick() {

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void showImageslist() {
        files = null;
        cropFilePath = new File(path + "/Crop/");
        if (!cropFilePath.exists()) {
            cropFilePath.mkdirs();
        }
        files = cropFilePath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                boolean booleanAccept = pathname.getName().endsWith(".jpg") || pathname.getName().endsWith(".png") || pathname.getName().endsWith("gif");
                if(booleanAccept) return  true;
                return false;
            }

        });
        ArrayCompareUtil.compareNameSortArray(files);
        ll_imageShow.removeAllViews();
        for (File photoFile : files) {
            ll_imageShow.addView(getImageView(photoFile.getAbsolutePath()));
        }
    }


    private View getImageView(String absolutePath) {

        Bitmap bitmap = decodeBitmapFromFile(absolutePath, 200, 200);
        LinearLayout layout = new LinearLayout(getApplicationContext());
        layout.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
        layout.setGravity(Gravity.CENTER);
        ImageView imageView = new ImageView(this);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == event.ACTION_DOWN) {
                     downX = event.getX();
                     downY = event.getY();
                }else if(event.getAction() == event.ACTION_UP){
                     upX = event.getX();
                     upY = event.getY();

                    if( downY - upY > 20 && (upX - downX <20 && upX - downX> -20 ) ){
                        Log.i("TAG","上滑有效");

                    }
                    Log.i("TAG","(down X = "+downX +",downY = "+downY + ")  ; (upX = "+upX +" ,upY = "+upY+")");
                }

                return true;
            }

        });
        imageView.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageBitmap(bitmap);
        layout.addView(imageView);

        return layout;
    }

    private Bitmap decodeBitmapFromFile(String absolutePath, int reqWidth, int reqHeight) {
        Bitmap bm = null;

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(absolutePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(absolutePath, options);

        return bm;
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
                                      int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    private void initViews() {
        tvUpdateImage = (TextView) findViewById(R.id.updateImage);
        currentImage = (ImageView) findViewById(R.id.currentImage);
        tvUpdateImage.setOnClickListener(this);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        ll_imageShow = (LinearLayout) findViewById(R.id.ll_imageShow);
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD卡正常",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"没有Sd卡，不能保存图片哦",Toast.LENGTH_SHORT).show();
        }
    }


    /*    附加选项	数据类型	描述
        crop	String	发送裁剪信号
        aspectX	int	X方向上的比例
        aspectY	int	Y方向上的比例
        outputX	int	裁剪区的宽
        outputY	int	裁剪区的高
        scale	boolean	是否保留比例
        return-data	boolean	是否将数据保留在Bitmap中返回
        data	Parcelable	相应的Bitmap数据
        circleCrop	String	圆形裁剪区域？
                MediaStore.EXTRA_OUTPUT ("output")	URI	将URI指向相应的file:///...，详见代码示例
        outputFormat	String	输出格式，一般设为Bitmap格式：Bitmap.CompressFormat.JPEG.toString()
        noFaceDetection	boolean	是否取消人脸识别功能*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.updateImage:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请选择").setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent;
                        sdcardTempFile = new File(path, "showImage" + System.currentTimeMillis() + ".jpg");
                        initFile();
                        imageUri = Uri.fromFile(sdcardTempFile);
                        switch (which) {
                            case 0://相机
                                takeCameraOnly(imageUri);
                                break;
                            case 1://相册
                                openGallery(imageUri);
                                break;
                        }
                    }
                }).create();
                AlertDialog alertDialog = builder.show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TO_ALBUM_PAGE:
                    Uri uri = data.getData();
                    cropImageFromCamera(uri, CAMERA_TO_CROP_PAGE);
                    break;

                case TO_CAMERA_PAGE:
                    cropImageFromCamera(imageUri, CAMERA_TO_CROP_PAGE);
                    break;
                case CAMERA_TO_CROP_PAGE:
                    Bundle extras = data.getExtras();
                    currentImage.setImageURI(imageCropUri);
                    if (extras != null) {
                        try {
                            Log.i("MianActivity", "extras!=null");
                            currentImage.setImageURI(imageCropUri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    showImageslist();
                    break;
                case ALBUM_TO_CROP_PAGE:
                    Bitmap photo = null;
                    Uri photoUri = data.getData();
                    if (photoUri != null) {
                        photo = BitmapFactory.decodeFile(photoUri.getPath());

                    }
                    if (photo == null) {
                        Bundle extra = data.getExtras();
                        if (extra != null) {
                            photo = (Bitmap) extra.get("data");//这里是null；
                        }
                    }
                    saveCroppedImage(photo);
                    currentImage.setImageBitmap(photo);
                    showImageslist();
                    break;

            }
        } else {
            Toast.makeText(PhotoCropUploadActivity.this, "image saved abnormal! ", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveCroppedImage(Bitmap bmp) {
        Log.e("TAG", "保存图片");
        File file = initFile();
        try {
            file.createNewFile();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (!bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)) {
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos)) {
                    bmp.compress(Bitmap.CompressFormat.JPEG, 20, fos);
                }
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void takeCameraOnly(Uri imageUri) {
        Intent intent = null;
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, TO_CAMERA_PAGE);
    }

    public void cropImageFromCamera(Uri uri, int requestCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        cropFilePath = new File(path + "/Crop/", "crop" + sdf.format(new Date(System.currentTimeMillis())) + ".jpg");
        imageCropUri = Uri.fromFile(cropFilePath);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 1000);
        intent.putExtra("outputY", 1000);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, requestCode);
    }

    public File initFile() {
        File cropFile;
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            cropFile = new File(path + "/Crop/", "crop" + System.currentTimeMillis() + ".jpg");
            if (cropFile.exists()) {
                cropFile.delete();
            }
            fileName = cropFile.getName();
            return cropFile;
        } else {
            Toast.makeText(PhotoCropUploadActivity.this, "没有SD卡", Toast.LENGTH_LONG).show();

        }
        return new File("");
    }

    /**
     * 打开相册
     */
    public void openGallery(Uri uri) {
        Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
        openAlbumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(openAlbumIntent, TO_ALBUM_PAGE);
    }

}