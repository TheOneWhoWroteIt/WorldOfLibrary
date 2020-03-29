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
                       <span style="margin: 0 20px 0 0">Поиск автора</span>
                       <form action="/libra/find_author" method="post">
                           <input type="search" name="search" placeholder="фамилия автора">
                           <button class="searchButton">Найти</button>
                       </form>
                   </div>
               </div>
           </div>
       </div>
       <div class="row" style="height: 82%">
           <div class="col-lg" >
               <div class="list_container">
                   <h2>Список авторов</h2>
                   <table class="table">
                       <thead style="font-size: 22px">
                       <tr>
                           <th>ID автора</th>
                           <th>Имя и Фамилия автора</th>
                           <th>Редактировать</th>

                       </tr>
                       </thead>
                       <tbody>

                            <#list authorsList as author>
                        <tr>

                            <td>
                                <p>${author.id}</p>

                            </td>
                            <td>
                                <form action="/libra/author_info" method="post" <#--style="display:inline!important; margin: 0 0 0 50px"-->>
                                    <button class="readernameButton" name="authorId" value="${author.id}" style="font-size: 18px">${author.firstName} ${author.lastName}</button>
                                </form>
                            </td>
                            <td>
                                <form action="/libra/edit_author" method="get">
                                    <button class="readernameButton" name="authorId" value="${author.id}" style="font-size: 18px">Редактировать</button>
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
                <@pagination.pagination objectList=authors pageNumber=numberPage url=pageUrl/>
           </div>
       </div>

   </div>

</#macro>

<@emplouee_room_menu title = "authors list" />
