<#include "macro_emplouee_room.ftl"/>

<#macro content_2>

    <#import "macro_libra_menu.ftl" as libra_ref>
    <@libra_ref.libra_menu/>

</#macro>

<#macro content_3>

   <div class="emplouee_various_lists">
       <div class="row" style="height: 10%">
           <div class="col-lg" >
               <div class="search_by_number">

                   <div class="search_container_emplouee">
                       <span style="margin: 0 20px 0 0">Поиск книги</span>
                       <form action="/libra/find_book_by_title" method="post">
                           <input type="search" name="search" placeholder="название">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Список книг</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>Название книги</th>
                           <th>Автор</th>
                           <th>Количество</th>
                           <th>Редактировать</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list booksList as book>
                        <tr>

                            <td>

                            <form action="/libra/book_info" method="get" >
                                <button class="authornameButton" name="bookId" value="${book.id}" style="font-size: 18px">${book.title}</button>
                            </form>
                            </td>
                            <td>
                                <#list book.authors as author>
                                <form action="/libra/author_info" method="post" >
                                    <button class="readernameButton" name="authorId" value="${author.id}" style="font-size: 18px">${author.firstName} ${author.lastName}</button>
                                </form>
                                </#list>
                            </td>
                            <td>
                                <p>${book.printEditionCount}</p>
                            </td>
                            <td>
                                <form action="/libra/edit_book" method="get">
                                    <button class="readernameButton" name="bookId" value="${book.id}" style="font-size: 18px">Редактировать</button>
                                </form>
                            </td>

                        </tr>
                            </#list>

                       </tbody>
                   </table>
               </div>
           </div>
       </div>
       <div class="row" style="height: 8%">
           <div class="col-lg" >
               <#import "macro_pagination.ftl" as pagination>
                <@pagination.pagination objectList=books pageNumber=numberPage url=pageUrl/>

           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "books list" />
