package models;

import java.sql.Connection;

import org.junit.Assert;

import patterns.UOW;

public abstract class DomainObject {

	private int ID;
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		Assert.assertNotNull("Cannot set a null ID", ID);
		this.ID = ID;
	}
	public DomainObject(int ID) {
		this.ID = ID;
	}
	
	public DomainObject() {
	}

	
	public abstract void Insert(Connection conn);
	public abstract void Update(Connection conn);
	public abstract void Delete(Connection conn);

	
	protected void markNew() {
		UOW.getCurrent().registerNew(this);
	}
	protected void markClean() {
		UOW.getCurrent().registerClean(this);
	}
	protected void markDirty() {
		UOW.getCurrent().registerDirty(this);
	}
	protected void markRemoved() {
		UOW.getCurrent().registerRemoved(this);
	}

}

