package edu.gduf.enums;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-28 11:43
 **/
public enum FileTypeEnum {

    /**
     *
     */
    XLS(".xls"),
    XLSX(".xlsx");

    private String fileType;

    FileTypeEnum(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
