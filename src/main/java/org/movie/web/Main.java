package org.movie.web;

import org.movie.dao.MovieDAO;
import org.movie.dao.QueryExecutor;
import org.movie.dao.ReviewDAO;
import org.movie.domain.MovieVO;
import org.movie.domain.ReviewVO;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        new MovieDAO().getList().stream().forEach(m -> System.out.println(m));
//
//        MovieVO vo = new MovieDAO().getMovie(8);
//        System.out.println(vo);
//
//        new MovieDAO().getImgs(8).stream().forEach(img -> System.out.println(img));

        new ReviewDAO().getAllReviews(8)
                .stream().forEach(v -> System.out.println(v));

        ReviewVO vo = new ReviewVO();
        vo.setMnum(8);
        vo.setCmt("한글 테스트");
        vo.setScore(3);

        new ReviewDAO().addReview(vo);

    }
}
