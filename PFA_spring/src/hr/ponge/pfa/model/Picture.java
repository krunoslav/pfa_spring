package hr.ponge.pfa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name = "PICTURE_SEQ", sequenceName = "PFA_PICTURE_SEQUENCE", allocationSize = 1, initialValue=1000)
@Entity
@Table(name="PFA_PICTURE")
public class Picture extends EntityPfa {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PICTURE_SEQ")
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	private User user;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private long picByteSize;

	@Column(name = "pic_byte_size", nullable = false)
	public long getPicByteSize() {
		return picByteSize;
	}

	public void setPicByteSize(long picByteSize) {
		this.picByteSize = picByteSize;
	}

	private long xSize;

	@Column(name = "x_size", nullable = false)
	public long getxSize() {
		return xSize;
	}

	public void setxSize(long xSize) {
		this.xSize = xSize;
	}

	private long ySize;

	@Column(name = "y_size", nullable = false)
	public long getySize() {
		return ySize;
	}

	public void setySize(long ySize) {
		this.ySize = ySize;
	}

	private byte[] picture;

	@Column(name = "picture", nullable = false, length = 10485760)
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	private String md5Code;

	@Column(name = "md5_code", nullable = false, length = 32)
	public String getMd5Code() {
		return md5Code;
	}

	public void setMd5Code(String md5Code) {
		this.md5Code = md5Code;
	}

}
