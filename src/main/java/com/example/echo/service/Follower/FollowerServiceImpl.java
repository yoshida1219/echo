package com.example.echo.service.Follower;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.Follower;
import com.example.echo.repository.FollowerListRpository;
import com.example.echo.repository.FollowerRepository;

@Service
@Transactional
public class FollowerServiceImpl implements FollowerService {

    @Autowired
    FollowerRepository inst;

    @Autowired
    FollowerListRpository List;

    @Override
    public Optional<Follower> OrderFollower(String user_id) {
        return inst.OrderFollower(user_id);
    }

    @Override
    public Optional<Follower> OrderFollowerPeople(String user_id) {
        return inst.OrderFollowerPeople(user_id);
    }

    @Override
    public Iterable<Follower> OrderFollowerList(String user_id){
        return List.OederFollowerList(user_id);
    }

    @Override
    public void FollowInsert(String user_id, String follow_user_id) {
        inst.insert(user_id, follow_user_id);
    }

    @Override
            public void FollowDelete(String user_id, String follow_user_id) {
                inst.delete(user_id, follow_user_id);
            }

    @Override
            public Optional<Integer> FindCheckFollow(String user_id, String follow_user_id) {
                return inst.OrderCheckFollow(user_id, follow_user_id);
            }
}
