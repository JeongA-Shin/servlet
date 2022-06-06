package group.servlet.repository;

import group.servlet.domain.member.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store=new HashMap<>();
    private static long sequence=0L;

    //singleton
    private static final MemberRepository instance=new MemberRepository();
    //싱글톤으로 만들 때에는 생성자를 반드시 private으로 해줘야 한다. 그래야 여러개 가 생성되는 것을 막을 수 있음
    private MemberRepository(){

    }

    public static MemberRepository getInstance(){
        return instance;
    }


    //저장
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    //id값으로 조회
    public Member findById(Long id){
        return store.get(id);
    }

    //전체 목록 조회
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    //전체 삭제
    public void clearStore(){
        store.clear();
    }
}
