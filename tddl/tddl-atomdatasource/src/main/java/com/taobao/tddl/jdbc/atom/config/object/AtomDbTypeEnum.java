/*(C) 2007-2012 Alibaba Group Holding Limited.	

import com.taobao.tddl.jdbc.atom.common.TAtomConstants;

/**
 * ���ݿ�����ö������
 * 
 * @author qihao
 *
 */
public enum AtomDbTypeEnum {

	ORACLE(TAtomConstants.DEFAULT_ORACLE_DRIVER_CLASS, TAtomConstants.DEFAULT_ORACLE_SORTER_CLASS),

	MYSQL(TAtomConstants.DEFAULT_MYSQL_DRIVER_CLASS, TAtomConstants.DEFAULT_MYSQL_SORTER_CLASS);

	private String driverClass;
	private String sorterClass;

	AtomDbTypeEnum(String driverClass, String sorterClass) {
		this.driverClass = driverClass;
		this.sorterClass = sorterClass;
	}

	public static AtomDbTypeEnum getAtomDbTypeEnumByType(String type) {
		try {
			return AtomDbTypeEnum.valueOf(type.trim().toUpperCase());
		} catch (Exception e) {
			return null;
		}
	}

	public String getDriverClass() {
		return driverClass;
	}

	public String getSorterClass() {
		return sorterClass;
	}
}