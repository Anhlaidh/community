package com.namesapce.community.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: page info
 * @author: Anhlaidh
 * @date: 2020/3/2 0002 22:59
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage ;


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount%size!=0) totalPage = (totalCount/size)+1;
        else totalPage=totalCount/size;
       pages.add(page);
            for (int j = page-1; j>0&&j>=page-3; j--) {
                pages.add(0, j);
            }
            for (int j = page+1;j<=totalPage&&j<=page+3;j++){
                pages.add(j);
            }
            if (pages.contains(1)) showFirstPage=false;
            else showFirstPage=true;
            if (pages.contains(totalPage)) showEndPage=false;
            else showEndPage=true;

        showPrevious = page != 1;

        showNext= !page.equals(totalPage);

    }
}
