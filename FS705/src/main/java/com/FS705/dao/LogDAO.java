package com.FS705.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.FS705.db.DBConnection;
import com.FS705.dto.LogDTO;
import com.FS705.util.Util;


public class LogDAO {
	
	//싱글턴 패턴 적용
	private LogDAO() {}
	private static LogDAO instance = new LogDAO();
	public static LogDAO getInstance() {
		return instance;
	}
	
	public static void insertLog(LogDTO logDto) {
		Connection conn = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO log(logIp, logTarget, logId, logEtc, logMethod) "
				+"VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, logDto.getLogIp());
			pstmt.setString(2, logDto.getLogTarget());
			pstmt.setString(3, logDto.getLogdId());
			pstmt.setString(4, logDto.getLogEtc());
			pstmt.setString(5, logDto.getLogMethod());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(null, pstmt, conn);
		}
		
	}

	public ArrayList<LogDTO> loglist(int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT logNo, logIp, logDate, logTarget, logId, logEtc, logMethod,"
				+ "(SELECT count(*) FROM log) as totalcount "
				+ "FROM log LIMIT ?, 20"; //20개씩 가져오기
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}

	public ArrayList<LogDTO> selectIpTarget(String ip, String target, int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="SELECT "
				+ "(SELECT count(*) FROM log WHERE logTarget=? AND logIp=?) "
				+ "as totalcount, "
				+ "logNo, logIp, logDate, logTarget, logId, logEtc, logMethod "
				+ "FROM log WHERE logTarget=? AND logIp=? limit ?, 20;" ;	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, target);
			pstmt.setString(2, ip);
			pstmt.setString(3, target);
			pstmt.setString(4, ip);
			pstmt.setInt(5, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}

	public ArrayList<LogDTO> selectIP(String ip, int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "SELECT "
				+"(SELECT count(*) FROM log WHERE logIp=?) as totalcount,"
				+"logNo, logIp, logDate, logTarget, logId, logEtc, logMethod"
				+" FROM log WHERE logIp=? limit ?, 20 ";	
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ip);
			pstmt.setString(2, ip);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}

	public ArrayList<LogDTO> selectTarget(String target, int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "SELECT "
				+"(SELECT count(*) FROM log WHERE logTarget=?) as totalcount,"
				+"logNo, logIp, logDate, logTarget, logId, logEtc, logMethod"
				+" FROM log WHERE logTarget=? limit ?, 20 ";	
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, target);
			pstmt.setString(2, target);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	//검색옵션에 들어갈 리스트
	public ArrayList<String> list(String column) {
		ArrayList<String> list = null;
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT DISTINCT " + column + " FROM log";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if( rs != null) {
				list = new ArrayList<String>();
			}while(rs.next()) {
				list.add(rs.getString(column));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<LogDTO> search(String searchName, String search, int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		System.out.println(searchName);
	//	System.out.println(search);
		String sql = "SELECT (SELECT count(*) FROM log WHERE "
				+ searchName
				+" LIKE CONCAT('%',?,'%')) as totalcount, "
				+"logNo, logIp, logDate, logId, logEtc, logTarget ,logMethod"
				+" FROM log WHERE " + searchName
				+" LIKE CONCAT('%',?,'%') limit ?, 20";
		 
		//where 0 은 전체가 나온다 
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}

	


	public ArrayList<LogDTO> searchAll(String searchName, String search, int page) {
		ArrayList<LogDTO> list = new ArrayList<LogDTO>();
		Connection con = DBConnection.dbconn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		String sql = "SELECT(SELECT count(*) FROM log WHERE MATCH(logIp, logId, logEtc, logTarget, logMethod) "
				+ "AGAINST(? IN BOOLEAN MODE)) as totalcount, logNo, logIp, logDate, logId, logEtc, logTarget, logMethod "
				+ "FROM log WHERE MATCH(logIp, logId, logEtc, logTarget, logMethod) " 
				+ "AGAINST(? IN BOOLEAN MODE) limit ?,20;";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setInt(3, page);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				LogDTO dto = new LogDTO();
				dto.setLogNo(rs.getInt("logNo"));
				dto.setLogIp(rs.getString("logIp"));
				dto.setLogDate(rs.getString("logDate"));
				dto.setLogTarget(rs.getString("logTarget"));
				dto.setLogdId(rs.getString("logId"));
				dto.setLogEtc(rs.getString("logEtc"));
				dto.setLogMethod(rs.getString("logMethod"));
				dto.setTotalCount(rs.getInt("totalcount"));
				list.add(dto);
				}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Util.closeAll(rs, pstmt, con);
		}
		return list;
	}
}
