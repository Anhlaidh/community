package com.namesapce.community.Service;

import com.namesapce.community.Enums.CommentTypeEnum;
import com.namesapce.community.Exception.CustomizeErrorCode;
import com.namesapce.community.Exception.CustomizeException;
import com.namesapce.community.Model.Comment;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: Anhlaidh
 * @date: 2020-06-22 17:32
 */
@Service
public class CommentService {

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

    }
}
