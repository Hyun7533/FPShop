package com.fpshop.board.notice;

import com.fpshop.model.notice.NoticeEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
    NoticeEntity selNotice(NoticeEntity ne);
}
