package com.example.mspl_connect.AdminEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "release_docs")
public class Releasenote {
	
	 public Releasenote() {
		super();
	}

	public Releasenote(Long id, String versionNumber, String documentPath, String module) {
		super();
		this.id = id;
		this.versionNumber = versionNumber;
		this.documentPath = documentPath;
		this.module = module;
	}

	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "version_number")
	    private String versionNumber;

	    @Column(name = "document_path")
	    private String documentPath;

	    @Column(name = "module")
	    private String module;
	    
	    private String released_date;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getVersionNumber() {
			return versionNumber;
		}

		public void setVersionNumber(String versionNumber) {
			this.versionNumber = versionNumber;
		}

		public String getDocumentPath() {
			return documentPath;
		}

		public void setDocumentPath(String documentPath) {
			this.documentPath = documentPath;
		}

		public String getModule() {
			return module;
		}

		public void setModule(String module) {
			this.module = module;
		}

		public String getReleased_date() {
			return released_date;
		}

		public void setReleased_date(String released_date) {
			this.released_date = released_date;
		}

		@Override
		public String toString() {
			return "Releasenote [id=" + id + ", versionNumber=" + versionNumber + ", documentPath=" + documentPath
					+ ", module=" + module + ", released_date=" + released_date + "]";
		}

}
