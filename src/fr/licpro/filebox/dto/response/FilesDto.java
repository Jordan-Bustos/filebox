/*
 * Software property of MILLAU Julien. Copyright Copyright (c) 2014.
 */
package fr.licpro.filebox.dto.response;

import fr.licpro.filebox.dto.commons.FileDto;
import fr.licpro.filebox.dto.error.HttpExceptionDto;
import java.util.Date;
import java.util.List;

/**
 * File dto
 *
 * @author julien
 */
public class FilesDto extends HttpExceptionDto {

    /**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 8424984371301106333L;

	/**
     * List of files
     */
    private List<FileDto> files;

    /**
     * Last update
     */
    private Date lastUpdate;

    public FilesDto(List<FileDto> listFile) {
        this.files = listFile;
    }

    public FilesDto() {
    }

    public List<FileDto> getListFile() {
        return files;
    }

    public void setListFile(List<FileDto> listFile) {
        this.files = listFile;
    }

    public Date getLastUpdate() {
		return lastUpdate;
	}
    
    public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
