package org.movie.dao;

import org.movie.domain.MovieVO;

import java.util.ArrayList;
import java.util.List;

public class MovieDAO { // DB에서 movie 정보를 가져옴

    public List<MovieVO> getList(){  // sql을 달려서 영화 정보들을 불러와서 vo에 넣어 저장후 리스트에 추가

        final List<MovieVO> list = new ArrayList<MovieVO>();
        final String sql ="select * from tbl_movie order by mnum desc";

        new QueryExecutor() {   // 중간부분만 바꿔주면 된다.처음(연결)과 끝(클로즈)은 다른것도 다 같으니까
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while(rs.next()){
                    MovieVO vo = new MovieVO();
                    vo.setMnum(rs.getInt("mnum"));
                    vo.setRuntime(rs.getInt("runtime"));
                    vo.setMtitle(rs.getString("mtitle"));
                    vo.setDirector(rs.getString("director"));
                    vo.setActors(rs.getString("actors"));
                    vo.setGrade(rs.getString("grade"));
                    vo.setOpendate(rs.getDate("opendate"));
                    vo.setPoster(rs.getString("poster"));
                    list.add(vo);
                }
            }
        }.executeAll();


        return list;
    }

    public MovieVO getMovie(final int mnum){ // 영화번호를 넣으면 영화를 불러옴
        final MovieVO vo = new MovieVO();
        final  String sql = "select * from tbl_movie where mnum = ?";

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1,mnum);
                rs = stmt.executeQuery();
                while(rs.next()){
                    vo.setMnum(rs.getInt("mnum"));
                    vo.setRuntime(rs.getInt("runtime"));
                    vo.setMtitle(rs.getString("mtitle"));
                    vo.setDirector(rs.getString("director"));
                    vo.setActors(rs.getString("actors"));
                    vo.setGrade(rs.getString("grade"));
                    vo.setOpendate(rs.getDate("opendate"));
                    vo.setPoster(rs.getString("poster"));
                }
            }
        }.executeAll();

        //code
        return vo;
    }

    public List<String> getImgs(int mnum){ //영화 번호를 넣으면 영화 이미지명'들'을 불러옴  '들'이라서 list
        List<String> list = new ArrayList<>();
        String sql = "select fname from tbl_movie_img where mnum= ? ";
        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, mnum);
                rs = stmt.executeQuery();
                while(rs.next()){
                    list.add(rs.getString("fname"));
                }
            }
        }.executeAll();

        //code
        return list;
    }


}
