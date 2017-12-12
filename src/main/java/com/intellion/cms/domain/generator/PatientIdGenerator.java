package com.intellion.cms.domain.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientIdGenerator implements IdentifierGenerator/*, Configurable*/ {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String tableName;
	
//    public void configure( Type arg0, Properties props, Dialect arg2 )  throws MappingException {
//        setTableName( props.getProperty( "tableName" ) );
//    }
	/*@Override
	public void configure(Type type, Properties props, ServiceRegistry serviceRegistry) throws MappingException {
		setTableName( props.getProperty( "tableName" ) );
	}*/
    public Serializable generate( SessionImplementor session, Object arg1 )
            throws HibernateException {
        // we want to open a new connection / transaction
        logger.debug("PatientIdGenerator generate method");
    	Serializable id = null;
        Connection connection = null;
        try {
        	
        	connection = session.connection();
        	try {               
                Long dbMaxId = getMaxIdFromDb(connection);
                id = generateId(dbMaxId,1);
            } catch (SQLException e) {
            	logger.error("Unable to generate PatientId Sequence", e);
                throw new HibernateException("Unable to generate PatientId Sequence");
            }
        	
        } finally {
			/*if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}*/
		}
        
        return id;
    }
    
    
    private int getCurrentYear() {
        Date date = new Date();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        String currentYearStr = dateFormat.format(date);
        int currentYear = Integer.valueOf(currentYearStr);
        return currentYear;
    }
    
    private int getCurrentMonth() {
        Date date = new Date();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("M");
        dateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        String currentYearStr = dateFormat.format(date);
        int currentYear = Integer.valueOf(currentYearStr);
        return currentYear;
    }
    

    
    private Long getMaxIdFromDb(Connection connection) throws SQLException {
    	Statement s1 = connection.createStatement();
        Long dbMaxId = -1L;
        StringBuilder sb = new StringBuilder();
        sb.append("select MAX(CAST((SUBSTR(cast(id as text), 7, 2)) || (SUBSTR(cast(id as text), 5, 2))|| (SUBSTR(cast(id as text), 1, 4)) AS numeric)) from patient");
        
        s1.execute(sb.toString());
        ResultSet rs1 = s1.getResultSet();
        while (rs1.next() ) {
        	String dbMaxIdStr = rs1.getString(1);
        	if(dbMaxIdStr!=null && !dbMaxIdStr.isEmpty())
        		dbMaxId = Long.valueOf(dbMaxIdStr);
        }
        logger.debug("dbMaxId-->{}",dbMaxId);
        return dbMaxId;
    }
    
    
    private int parseYear(Long notAId) {
    	if(notAId != null && notAId > 0) {
    		String notAIdString = notAId.toString();
    		String yearStr = notAIdString.substring(0, 2);
    		int year = Integer.valueOf(yearStr);
    		return year;
    	}
    	
    	return -1;
    }
    
    private int parseMonth(Long notAId) {
    	if(notAId != null && notAId > 0) {
    		String notAIdString = notAId.toString();
    		String yearStr = notAIdString.substring(2, 4);
    		int year = Integer.valueOf(yearStr);
    		return year;
    	}
    	
    	return -1;
    }
    
    private int parseId(Long notAId) {
    	if(notAId != null && notAId > 0) {
    		String notAIdString = notAId.toString();
    		String yearStr = notAIdString.substring(4);
    		int year = Integer.valueOf(yearStr);
    		return year;
    	}
    	
    	return -1;
    }

    
    private String getId(int id, int month, int year) {
    	String yearStr = StringUtils.leftPad("" + year,2, '0');
    	String monthStr = StringUtils.leftPad("" + month,2, '0');
    	String idStr = StringUtils.leftPad("" + id,4, '0');
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(idStr);
//    	sb.append("/");
    	sb.append(monthStr);
//    	sb.append("/");
    	sb.append(yearStr);
    	return sb.toString();
    }
    public Serializable generateId(Long dbMaxId,int incrementValue) {
        int dbYear = parseYear(dbMaxId);
        int dbMonth = parseMonth(dbMaxId);
        int dbId = parseId(dbMaxId);
        int currentYear = getCurrentYear();
        int currentMonth = getCurrentMonth();
        Serializable retId = -1;
        if(dbYear == currentYear && dbMonth == currentMonth) {
        	retId = getId(dbId + incrementValue, currentMonth, currentYear);
        } else {
        	retId = getId(incrementValue, currentMonth, currentYear);
        }
        logger.debug("generateId-->{}",retId);
        return retId;
    }
    
    public static void main(String[] args) {
    	PatientIdGenerator gen = new PatientIdGenerator();
    	//System.out.println(gen.temp(15040001L));
    	Long notAId = 15049998L;
		System.out.println(gen.parseYear(notAId));
		System.out.println(gen.parseMonth(notAId));
		System.out.println(gen.parseId(notAId));
		System.out.println("month : " + gen.getCurrentMonth() );
		System.out.println("year : " + gen.getCurrentYear() );
		System.out.println("ret Id : " + gen.generateId(notAId,1));
	}

    // getter & setter for the configurable parameters
    public String getTableName() {
        return tableName;
    }
    public void setTableName( String tableName ) {
    	this.tableName = tableName;
    }


}