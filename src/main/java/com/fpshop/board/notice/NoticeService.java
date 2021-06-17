package com.fpshop.board.notice;

import com.fpshop.model.notice.NoticeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

    @Autowired
    private NoticeMapper mapper;

    public NoticeEntity selNotice(NoticeEntity ne) {
        return null;
    }
}
