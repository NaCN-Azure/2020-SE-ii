package com.example.hotel.data.hotel;


import com.example.hotel.po.HistoryComment;
import com.example.hotel.vo.HistoryCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CommentMapper {



    int addComment(HistoryComment historycomment);

    List<HistoryComment> selectAllComments(Integer hotelId);

}
