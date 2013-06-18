/*(C) 2007-2012 Alibaba Group Holding Limited.	

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetArrayHandler implements ParameterHandler {
	public void setParameter(PreparedStatement stmt, Object[] args) throws SQLException {
		stmt.setArray((Integer) args[0], (Array) args[1]);
	}
}