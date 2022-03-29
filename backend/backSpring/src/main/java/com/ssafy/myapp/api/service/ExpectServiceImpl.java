package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.db.entity.Expectation;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.repository.ExpectRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import com.ssafy.myapp.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("expectService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExpectServiceImpl implements ExpectService {

    private final UserRepository userRepository;
    private final ExpectRepository expectRepository;
    private final ShowRepository showRepository;

    @Override
    public List<Expectation> findExpectation(Long id) {
        Optional<Show> oExpect = showRepository.findById(id);
        return oExpect.get().getExpectations();
    }

    @Override
    @Transactional
    public Long addExpectation(Long id, ReviewRegisterReq expectInfo) {
        // 엔티티 조회
        Long userId = expectInfo.getUserId();
        User findUser = userRepository.findById(userId).get();

        Show findShow = showRepository.findById(id).get();

        // 기대평 생성
        Expectation expectation = Expectation.createExpect(findUser, findShow, expectInfo);
        expectRepository.save(expectation);

        return expectation.getId();
    }

    @Override
    @Transactional
    public boolean modifyExpectation(Long expectationId, ReviewRegisterReq expectInfo) {
        Optional<Expectation> oExpect = expectRepository.findById(expectationId);

        if(!oExpect.isPresent()) return false;

        Expectation expectation = oExpect.get();

        expectation.setTitle(expectInfo.getTitle());
        expectation.setContents(expectInfo.getContents());

        expectRepository.save(expectation);
        return true;
    }

    @Override
    @Transactional
    public boolean removeExpectation(Long expectationId) {
        Optional<Expectation> oExpect = expectRepository.findById(expectationId);
        if(oExpect.isPresent()) {
            expectRepository.delete(oExpect.get());
            return true;
        }
        return false;
    }
}
