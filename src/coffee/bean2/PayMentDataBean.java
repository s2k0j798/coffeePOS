package coffee.bean2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PayMentDataBean {
	private static PayMentDataBean INSTANCE = new PayMentDataBean();

	public PayMentDataBean() {
		// TODO Auto-generated constructor stub
	}

	public static PayMentDataBean getINSTANCE() {
		return INSTANCE;
	}

	private Connection getConnection() throws Exception {
		// TODO Auto-generated method stub

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");

		return ds.getConnection();
	}

	private Timestamp getDate() {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select now()";
		Timestamp now = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				now = rs.getTimestamp(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return now;

	}

	public int payMentInsertAction(int ptUsed, int pMoney, String cId, int pointMoney) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("결제 인서트 부분");
		Random r = new Random();
		int random = r.nextInt(10000);
		String pCode = "c" + random;
		Timestamp pDate = getDate();

		String query = "insert into payment values(?,?,?,?,?)";
		int x = 0;
		try {

			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pCode);
			pstmt.setTimestamp(2, pDate);
			pstmt.setInt(3, ptUsed);
			pstmt.setInt(4, pMoney);
			pstmt.setString(5, cId);
			x = pstmt.executeUpdate();

			System.out.println("결제 행 : " + x);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.pointListAction(pCode, cId, pointMoney); // 호출 인서트
			this.updateUserPoint(cId, pMoney, ptUsed); //클라이언트 업데이트
		}

		return x;

	}

	public void pointListAction(String pCode, String cId, int pointMoney) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("고객 포인트 인서트 부분");
		String query = "insert into pointlist values(?,?,?)";
		int x = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pCode);
			pstmt.setString(2, cId);
			pstmt.setInt(3, pointMoney);
			x = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

	public void updateUserPoint(String cId, int pMoney, int ptUsed) {
		// TODO Auto-generated method stub
		System.out.println("업데이트 유저 들어오니??");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String query = "update client" + "set cPoint=cPoint+?, " + "totalPoint=totalPoint+?" + "where cId=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cId);
			pstmt.setInt(2, pMoney);
			pstmt.setInt(3, ptUsed);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null)
					pstmt.close();
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("업데이트 끝냈니");
	}

}
