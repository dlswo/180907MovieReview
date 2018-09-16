package org.movie.dao;

public interface Executor { // 중간 이곳은 각자 실행마다 달라서 인터페이스로 따로 빼서 무조건 주게함.(강제성)

    public void doJob()throws Exception;
}
