/*
 * Software property of MILLAU Julien. Copyright Copyright (c) 2014.
 */
package fr.licpro.filebox.dto.commons;

import fr.licpro.filebox.dto.GenericDto;
import fr.licpro.filebox.dto.enums.FileTypeEnum;
import fr.licpro.filebox.utils.FileboxConstant;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * File DTO
 *
 * @author julien
 */
@DatabaseTable(tableName=FileboxConstant.DATABASE_NAME)
public class FileDto extends GenericDto {

    /**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -5039069813601889015L;
	
	/**
	 * Id of the object in database.
	 */
	@DatabaseField(generatedId = true)
	private long id;

	/**
     * File name
     */
	@DatabaseField
    private String mName;

    /**
     * File id Hash
     */
	@DatabaseField
    private String mHashId;

    /**
     * Is folder
     */
	@DatabaseField
    private Boolean mIsFolder;

    /**
     * File type
     */
	@DatabaseField
    private FileTypeEnum mFileType;

    /**
     * Last modification date
     */
	@DatabaseField
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

	/**
	 * Getter of the id.
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter of the id.
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

}
