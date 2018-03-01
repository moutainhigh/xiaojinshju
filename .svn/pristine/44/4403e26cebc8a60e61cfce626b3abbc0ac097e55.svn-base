package fengkongweishi.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.message.ImageInfo;
import fengkongweishi.util.FailResponse;
import fengkongweishi.util.FileFormat;
import fengkongweishi.util.FileFormatJudge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @author Administrator
 */
@Service
public class FileUploadService {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    private static final String POINT = ".";
    private static final String SLASH = "/";
    public static final String FILE_TYPE_IMAGE = "image";
    public static final String FILE_TYPE_FILE = "file";


    public OssFile uploadByIOStream(InputStream fileInputStream) {
        Map<String, Object> fileInfoMap = fileInformation(fileInputStream);
        upload(fileInfoMap, fileInputStream);
        return new OssFile("", (String) fileInfoMap.get("newFileName"),
                Long.valueOf(fileInfoMap.get("fileSize").toString()), (String) fileInfoMap.get("fileFormatString"), "file");
    }

    /**
     * 文件信息
     */
    private Map<String, Object> fileInformation(InputStream is) {
        Map<String, Object> fileInfoMap = new HashMap<>();
        UUID randomUUID = UUID.randomUUID();
        String newFileName = randomUUID.toString() + ".zip";
        // 文件大小
        int fileSize;
        try {
            fileSize = is.available();
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_IO_ERROR);
        }
        fileInfoMap.put("fileSize", fileSize);
        fileInfoMap.put("newFileName", newFileName);

        // 上传到阿里云的返回的文件地址
        StringBuilder sb2 = new StringBuilder();
        fileInfoMap.put("fileFormatString", "ZIP");
        fileInfoMap.put("realfileFormat", "ZIP");
        return fileInfoMap;
    }

    /**
     * 文件信息
     */
    private Map<String, Object> fileInformation(MultipartFile file) {
        Map<String, Object> fileInfoMap = new HashMap<>();

        // 文件原名
        String originalFilename = file.getOriginalFilename();
        fileInfoMap.put("originalFilename", originalFilename);

        // 文件大小
        Long fileSize = file.getSize();
        fileInfoMap.put("fileSize", fileSize);

        // 新文件名
        String suffix = "";
        if (originalFilename.lastIndexOf(POINT) != -1) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf(POINT) + 1);
        }
        UUID randomUUID = UUID.randomUUID();


        fileInfoMap.put("randomUUID", randomUUID.toString());


        // 文件类型
        String fileFormatString = "";
        FileFormat realfileFormat;
        try {
            realfileFormat = FileFormatJudge.getFormat(file.getInputStream());
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_LASTFILE_ERROR);
        }
        if (realfileFormat != null) {
            fileFormatString = realfileFormat.toString();
        } else if (!"".equals(suffix)) {
            fileFormatString = suffix.toUpperCase();
        }
        String newFileName;
        if ("".equals(suffix)) {
            newFileName = randomUUID.toString() + POINT + fileFormatString;
        } else {
            newFileName = randomUUID.toString() + POINT + suffix;
        }
        fileInfoMap.put("newFileName", newFileName);


        // 上传到阿里云的返回的文件地址
        String ossFileUrl = new StringBuilder(bucketName).append(POINT).append(endpoint).append(SLASH).append(newFileName)
                .toString();
        fileInfoMap.put("ossFileUrl", ossFileUrl);

        fileInfoMap.put("fileFormatString", fileFormatString);
        fileInfoMap.put("realfileFormat", realfileFormat);

        return fileInfoMap;
    }

    /**
     * 文件上传，返回文件阿里云地址
     */
    public String upload(Map<String, Object> fileInformation, InputStream fileInputStream) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String ossFileReturnTemplate = new StringBuilder(bucketName).append(POINT).append(endpoint).append(SLASH).toString();
        String newFileName = (String) fileInformation.get("newFileName");
        try {
            ossClient.putObject(new PutObjectRequest(bucketName, newFileName, fileInputStream));
        } catch (OSSException oe) {
            throw new FailResponse(1100,
                    new StringBuilder(oe.getErrorCode()).append(":").append(oe.getErrorMessage()).toString());
        } finally {
            ossClient.shutdown();
        }
        return ossFileReturnTemplate + newFileName;
    }


    public OssFile uploadByFileTypeName(MultipartFile file, String fileType) {
        Map<String, Object> fileInfoMap = fileInformation(file);
        // 文件类型判断
        if (FILE_TYPE_IMAGE.equals(fileType)) {
            if (!FileFormatJudge.isImage((FileFormat) fileInfoMap.get("realfileFormat"))) {
                throw new FailResponse(ExceptionEnum.FILEUPLOAD_PICTURE_FORMAT_ERROR);
            }

            try {
                upload(fileInfoMap, file.getInputStream());
            } catch (IOException e1) {
                throw new FailResponse(ExceptionEnum.FILEUPLOAD_PICTURE_UPLOAD_ERROR);
            }
            try {
                OssImage imageContent;
                imageContent = transferImage(fileInfoMap, file.getInputStream());
                return imageContent;
            } catch (IOException e) {
                throw new FailResponse(ExceptionEnum.FILEUPLOAD_UNKNOWN_ERROR);
            }

        } else if (FILE_TYPE_FILE.equals(fileType)) {
            String newFileAddress = "";
            try {
                upload(fileInfoMap, file.getInputStream());
            } catch (IOException e) {
                throw new FailResponse(ExceptionEnum.FILEUPLOAD_COMMONFILE_UPLOAD_ERROR);
            }
            return new OssFile((String) fileInfoMap.get("originalFilename"), (String) fileInfoMap.get("newFileName"),
                    (Long) fileInfoMap.get("fileSize"), (String) fileInfoMap.get("fileFormatString"), "file");
        } else {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_FILE_FORMAT_ERROR);
        }
    }

    public OssImage transferImage(Map<String, Object> fileInformation, InputStream fileInputStream) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        String ossFileReturnTemplate = new StringBuilder(bucketName).append(POINT).append(endpoint).append(SLASH).toString();
        String randomUUIDString = (String) fileInformation.get("randomUUID");
        String transNewFileName = new StringBuilder(randomUUIDString).append(".jpg").toString();
        String transImageNameTemplate = new StringBuilder(randomUUIDString).append("%s").append(".jpg").toString();
        OssImage ossImage = new OssImage();
        ossImage.setFileType((String) fileInformation.get("fileFormatString"));
        ossImage.setNewFileName(fileInformation.get("newFileName").toString());
        ossImage.setOriginalFileName((String) fileInformation.get("originalFilename"));
        ossImage.setFileSize((Long) fileInformation.get("fileSize"));
        ossImage.setUploadType("image");
        try {
            if ("JPEG".equals(fileInformation.get("fileFormatString"))) {
                ossImage.setImageInfo(picutureTrans_198_720(ossClient, transImageNameTemplate,
                        (String) fileInformation.get("newFileName"), fileInputStream));
                return ossImage;
            } else {
                GetObjectRequest request = picutureTrans(ossClient, "image/format,jpg",
                        (String) fileInformation.get("newFileName"), transNewFileName);
                ossImage.setImageInfo(picutureTrans_198_720(ossClient, transImageNameTemplate, transNewFileName,
                        ossClient.getObject(request).getObjectContent()));
                return ossImage;
            }
        } catch (Exception e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_ALIYUN_FILE_ERROR);
        } finally {
            ossClient.shutdown();
        }
    }

    private GetObjectRequest picutureTrans(OSSClient ossClient, String style, String primitiveFileName,
                                           String transNewFileName) {

        GetObjectRequest request = new GetObjectRequest(bucketName, primitiveFileName);
        // if(style!=null){
        request.setProcess(style);
        // }
        OSSObject ossObject = ossClient.getObject(request);
        InputStream inputStream = ossObject.getObjectContent();
        ossClient.putObject(new PutObjectRequest(bucketName, transNewFileName, inputStream));
        return request;
    }

    private ImageInfo[] picutureTrans_198_720(OSSClient ossClient, String transFileNameTemplate,
                                              String transNewFileName, InputStream fileInputStream) {
        ImageInfo[] imageInfos;
        StringBuilder sb9 = new StringBuilder();
        String ossFileReturnTemplate = "";
        try {
            BufferedImage bufferedImage = ImageIO.read(fileInputStream);
            Collection<Integer> colls = new ArrayList<>();
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            int size = 0;
            colls.add(height);
            colls.add(width);
            Integer min = Collections.min(colls);
            imageInfos = new ImageInfo[3];
            if (min >= 198) {
                GetObjectRequest request = picutureTrans(ossClient, "image/resize,m_mfit,w_198", transNewFileName,
                        String.format(transFileNameTemplate, "_198"));
                imageInfos[0] = imageInfoCreate(3, 0, 0, 0,
                        ossFileReturnTemplate + String.format(transFileNameTemplate, "_198"));
                if (min >= 720) {
                    request = picutureTrans(ossClient, "image/resize,m_mfit,w_720", transNewFileName,
                            String.format(transFileNameTemplate, "_720"));
                    imageInfos[1] = imageInfoCreate(2, 0, 0, 0,
                            ossFileReturnTemplate + String.format(transFileNameTemplate, "_720"));
                }
            }
            if (imageInfos[0] == null) {
                for (int i = 1; i < 4; i++) {
                    imageInfos[i - 1] = imageInfoCreate(i, height, width, size,
                            ossFileReturnTemplate + transNewFileName);
                }
            } else if (imageInfos[1] == null) {
                imageInfos[1] = imageInfoCreate(2, 0, 0, size,
                        ossFileReturnTemplate + String.format(transFileNameTemplate, ""));
                imageInfos[2] = imageInfoCreate(1, height, width, size, ossFileReturnTemplate + transNewFileName);
            } else {
                imageInfos[2] = imageInfoCreate(1, height, width, size, ossFileReturnTemplate + transNewFileName);
            }
            return imageInfos;
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_ALIYUN_PICTURE_ERROR);
        }
    }

    private ImageInfo imageInfoCreate(Integer type, Integer height, Integer width, Integer size, String url) {
        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setType(type);
        imageInfo.setHeight(height);
        imageInfo.setWidth(width);
        imageInfo.setSize(size);
        imageInfo.setUrl(url);
        return imageInfo;
    }

    public static class OssImage extends OssFile {

        private ImageInfo[] imageInfo;

        public ImageInfo[] getImageInfo() {
            return imageInfo;
        }

        public void setImageInfo(ImageInfo[] imageInfo) {
            this.imageInfo = imageInfo;
        }

        public OssImage(String originalFileName, String newFileName, Long fileSize, String fileType, String uploadType) {
            super(originalFileName, newFileName, fileSize, fileType, uploadType);
        }

        public OssImage() {
        }
    }

    public static class OssFile {
        private String originalFileName;
        private String newFileName;
        private Long fileSize;
        private String fileType;
        private String uploadType;
        /**
         * 顶级目录
          */
        private String topcategory = "-";
        /**
         * 子级目录
         */
        private String subcategory = "-";

        public String getTopcategory() {
            return topcategory;
        }

        public void setTopcategory(String topcategory) {
            this.topcategory = topcategory;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

        public String getUploadType() {
            return uploadType;
        }

        public void setUploadType(String uploadType) {
            this.uploadType = uploadType;
        }

        public String getOriginalFileName() {
            return originalFileName;
        }

        public void setOriginalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
        }

        public String getNewFileName() {
            return newFileName;
        }

        public void setNewFileName(String newFileName) {
            this.newFileName = newFileName;
        }

        public Long getFileSize() {
            return fileSize;
        }

        public void setFileSize(Long fileSize) {
            this.fileSize = fileSize;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }

        public OssFile(String originalFileName, String newFileName, Long fileSize, String fileType, String uploadType) {
            super();
            this.originalFileName = originalFileName;
            this.newFileName = newFileName;
            this.fileSize = fileSize;
            this.fileType = fileType;
            this.uploadType = uploadType;
        }

        public OssFile() {

        }

    }

    // 重载

    public OssImage uploadByUrlAndFileType(String urlString, String fileType) {
        try {
            URL url = new URL(urlString);
            Map<String, Object> fileInfoMap = fileInformation(url);
            // 文件类型判断
            if (FILE_TYPE_IMAGE.equals(fileType)) {
                if (!FileFormatJudge.isImage((FileFormat) fileInfoMap.get("realfileFormat"))) {
                    throw new FailResponse(ExceptionEnum.FILEUPLOAD_PICTURE_FORMAT_ERROR);
                }
                upload(fileInfoMap, url.openStream());
                return transferImage(fileInfoMap, url.openStream());
            } else if (FILE_TYPE_FILE.equals(fileType)) {
                upload(fileInfoMap, url.openStream());
                return new OssImage((String) fileInfoMap.get("originalFilename"), (String) fileInfoMap.get("newFileName"),
                        (Long) fileInfoMap.get("fileSize"), (String) fileInfoMap.get("fileFormatString"), "file");
            } else {
                throw new FailResponse(ExceptionEnum.FILEUPLOAD_FILE_FORMAT_ERROR);
            }
        } catch (MalformedURLException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_URL_FORMAT_ERROR);
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_URL_FILE_ERROR);
        }
    }

    private Map<String, Object> fileInformation(URL url) {
        Map<String, Object> fileInfoMap = new HashMap<>();
        // 文件原名
        String originalFilename = url.getFile();
        fileInfoMap.put("originalFilename", originalFilename);
        // 文件大小
        Long fileSize;
        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            fileSize = con.getContentLengthLong();
            fileInfoMap.put("fileSize", fileSize);
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_REMOTE_SERVER_ERROR);
        }

        // 新文件名
        String suffix = "";
        if (originalFilename.lastIndexOf(POINT) != -1) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf(POINT) + 1);
        }

        // 文件类型
        String fileFormatString = "";
        FileFormat realfileFormat;
        try {
            realfileFormat = FileFormatJudge.getFormat((url.openConnection()).getInputStream());
            if (realfileFormat != null) {
                fileFormatString = realfileFormat.toString();
            } else if (!"".equals(suffix)) {
                fileFormatString = suffix.toUpperCase();
            }
            fileInfoMap.put("fileFormatString", fileFormatString);
            fileInfoMap.put("realfileFormat", realfileFormat);
        } catch (IOException e) {
            throw new FailResponse(ExceptionEnum.FILEUPLOAD_REMOTE_SERVER_ERROR);
        }


        UUID randomUUID = UUID.randomUUID();
        String newFileName;
        if ("".equals(suffix)) {
            newFileName = randomUUID.toString() + POINT + fileFormatString.toLowerCase();
        } else {
            newFileName = randomUUID.toString() + POINT + suffix;
        }
        fileInfoMap.put("newFileName", newFileName);
        fileInfoMap.put("randomUUID", randomUUID.toString());

        // 上传到阿里云的返回的文件地址
        String ossFileUrl = bucketName + POINT + endpoint + SLASH + newFileName;
        fileInfoMap.put("ossFileUrl", ossFileUrl);


        return fileInfoMap;
    }
}
