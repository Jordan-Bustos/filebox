/*
 * Software property of MILLAU Julien. Copyright Copyright (c) 2014.
 */
package fr.licpro.filebox.dto.commons;

import fr.licpro.filebox.dto.GenericDto;
import fr.licpro.filebox.dto.enums.FileTypeEnum;
import java.util.Date;

/**
 * File DTO
 *
 * @author julien
 */
public class FileDto extends GenericDto {

    /**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -5039069813601889015L;

	/**
     * File name
     */
    private String mName;

    /**
     * File id Hash
     */
    private String mHashId;

    /**
     * Is folder
     */
    private Boolean mIsFolder;

    /**
     * File type
     */
    private FileTypeEnum mFileType;

    /**
     * Last modification date
     */
    private Date lastModification;

    /**
     * Consctructor using field
     *
     * @param name
     * @param hashId
     * @param isFolder
     * @param mimeType
     * @param pDate
     */
    public FileDto(String name, String hashId, Boolean isFolder, FileTypeEnum mimeType, Date pDate) 
    {
        setName(name);
        setHashId(hashId);
        setIsFolder(isFolder);
        setFileType(mimeType);
        setLastModification(pDate);
    }

    /**
     * Default constructor
     */
    public FileDto() {
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getHashId() {
        return mHashId;
    }

    public void setHashId(String hashId) {
        this.mHashId = hashId;
    }

    public Boolean isIsFolder() {
        return mIsFolder;
    }

    public void setIsFolder(Boolean isFolder) {
        this.mIsFolder = isFolder;
    }

    public FileTypeEnum getFileType() {
        return mFileType;
    }

    public void setFileType(FileTypeEnum fileType) {
        this.mFileType = fileType;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

}
