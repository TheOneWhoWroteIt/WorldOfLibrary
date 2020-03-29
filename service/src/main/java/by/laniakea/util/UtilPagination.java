package by.laniakea.util;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
public class UtilPagination<T> {

    public Integer paginationNumber(String str){

        int pageNumber;
        if(str == null){
            pageNumber = 0;
        }else{
            pageNumber = Integer.valueOf(str);

        }

        return pageNumber;
    }


    public Pageable pageableForm(int size, int page, String sortElement){

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, sortElement);
        return pageable;
    }


    public Integer paginationNextAndPrevious(Page<T> pages, int pageNumber){

        if(pageNumber > pages.getTotalPages()){
            pageNumber = pages.getTotalPages();
        }
        else if(pageNumber < 0){
            pageNumber = 0;
        }

        return pageNumber;
    }
}
