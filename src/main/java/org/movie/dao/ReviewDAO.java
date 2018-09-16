package org.movie.dao;

import org.movie.domain.ReviewVO;

import java.util.ArrayList;
import java.util.List;

public class ReviewDAO {

    public void updateChoice(int rno, String value){ // 리뷰 번호와 (좋아요or싫어요)을 넣으면 sql을 통해 업데이트

        System.out.println("RNO: " + rno);
        System.out.println("VALUE: " + value);

        String sql = "update tbl_review set ";
        if(value.equals("g")){
            sql += "good = nvl(good,0) + 1 ";
        }else{
            sql += "bad = nvl(bad,0) + 1 ";
        }
        sql += " where rno =? ";

        String finalSql = sql;
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                System.out.println(finalSql);
                stmt = con.prepareStatement(finalSql);
                stmt.setInt(1, rno);
                stmt.executeUpdate();
            }
        }.executeAll();

    }

    public void addReview(ReviewVO vo){
        String sql = "insert into tbl_review (mnum, score, cmt, rno)\n" +
                "values ( ?, ?, ?, seq_review.nextval)";
        //mnum, score, cmt
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, vo.getMnum());
                stmt.setInt(2, vo.getScore());
                stmt.setString(3,vo.getCmt());
                stmt.executeUpdate();
            }
        }.executeAll();
    }

    public List<ReviewVO> getAllReviews(int mnum){
        List<ReviewVO> list = new ArrayList<>();
        String sql ="select \n" +
                "  mnum,score, cmt, regdate, rno, \n" +
                "  nvl(good,0) good, nvl(bad,0) bad \n" +
                "from tbl_review \n" +
                "where mnum = ? \n" +
                "order by mnum desc";
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, mnum);
                rs = stmt.executeQuery();
                while(rs.next()){
                    ReviewVO vo = new ReviewVO();
//                    "  mnum,score, cmt, regdate, rno, \n" +
//                    "  nvl(good,0) good, nvl(bad,0) bad \n" +
                    vo.setMnum(rs.getInt(1));
                    vo.setScore(rs.getInt(2));
                    vo.setCmt(rs.getString(3));
                    vo.setRegdate(rs.getDate(4));
                    vo.setRno(rs.getInt(5));

                    vo.setGood(rs.getInt(6));
                    vo.setBad(rs.getInt(7));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }
}
