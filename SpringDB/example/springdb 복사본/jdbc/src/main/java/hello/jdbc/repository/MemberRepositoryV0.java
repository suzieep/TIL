package hello.jdbc.repository;


import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriverManager 사용
 */

@Slf4j
public class MemberRepositoryV0 {
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?,?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        con = getConnection();
        try {

            pstmt = con.prepareStatement(sql); //prepareStatement -> (파라미터 바인딩 방식)변수 지정까지 해주는 거 그냥 Statement는 ? 없는 거
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate(); //실행
            return member;
        } catch (SQLException e) {
            log.error("db error",e);
            throw e; //예외 밖으로 던지기
        } finally {

            close(con,pstmt,null);
        }

    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id =?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("member not found memberId="+memberId);
            }
        }catch (SQLException e){
            log.error("db error",e);
            throw e;
        } finally {
            close(con,pstmt,rs);
        }
    }
    public void update(String memberId, int money) throws SQLException {
        String sql ="update member set money=? where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}",resultSize);
        } catch (SQLException e) {
            log.error("db error",e);
            throw e;
        } finally {

            close(con,pstmt,null);
        }
    }

    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs)   { //사용한 자원 모두 릴리즈 해주기
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error",e);
            }

        }

        //위에서 e 터지면 con 못 닫으니까 따로 try-catch 달아줘야함
        if(stmt != null){
            try {
                stmt.close(); //SQLException이 터져도 con도 닫을 수 있게됨
            } catch (SQLException e) {
                log.info("error",e);
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error",e);
            }
        }
    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
