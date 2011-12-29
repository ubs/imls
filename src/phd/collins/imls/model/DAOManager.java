package phd.collins.imls.model;

import java.sql.SQLException;
import java.util.HashMap;

import phd.collins.imls.exceptions.ConnectionSourceNotInitalizedException;
import phd.collins.imls.util.Info;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class DAOManager {
	protected static ConnectionSource _connectionSource;
	protected static HashMap<String, Dao<?, ?>> _hashmapDAO = new HashMap<String, Dao<?, ?>>();
	
	public static final Dao<User, Long> USER_DAO = getDAO(User.class);
	public static final Dao<Admin, Long> ADMIN_DAO = getDAO(Admin.class);
	public static final Dao<Student, Long> STUDENT_DAO = getDAO(Student.class);
	
//	public static final Dao<User, Long> STUDY_AREA_DAO = getDAO(StudyArea.class);
	
	private static ConnectionSource getConnectionSource() throws ConnectionSourceNotInitalizedException, Exception {
		if (_connectionSource == null){
			_connectionSource = ConnectionManager.getDatabaseConnection();
			if (_connectionSource == null){
				throw new ConnectionSourceNotInitalizedException();
			}
		}
		return _connectionSource;
	}
	
	@SuppressWarnings("unchecked")
	protected static <T> Dao<T, Long> getDAO(Class<T> clazz){
		String hashMapDAO_Key = getHashMapDAOKey(clazz.toString());
		
		if (!_hashmapDAO.containsKey(hashMapDAO_Key)){
			try {
				Dao<T, Long> clazzDAO = DaoManager.createDao(getConnectionSource(), clazz);
				_hashmapDAO.put(hashMapDAO_Key, clazzDAO);
			}
			catch (SQLException e) { defaultExceptionHandler(e); }
			catch (ConnectionSourceNotInitalizedException e) { defaultExceptionHandler(e); }
			catch (Exception e) { defaultExceptionHandler(e); }
		}
		return (Dao<T, Long>) _hashmapDAO.get(hashMapDAO_Key);
	}
	
	protected static String getHashMapDAOKey(String str){
		return str.replaceAll("\\s+", "_");
	}
	
	public static void clearHashMapDAO(){
		_hashmapDAO.clear();
	}
	
	private static void defaultExceptionHandler(Exception ex){
		Info.serr(ex.getMessage());
		ex.printStackTrace();
	}
}
