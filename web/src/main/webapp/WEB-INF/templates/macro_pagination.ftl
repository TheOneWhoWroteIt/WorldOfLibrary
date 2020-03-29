<#macro pagination objectList pageNumber url>

 <nav aria-label="Page navigation example">
     <ul class="pagination justify-content-center">

         <#assign count = objectList.getTotalPages()>
         <#if count = 0>
             <li class="page-item active"><a class="page-link" href="#" >1</a></li>
         <#else >
                        <#if count < 3>
                            <#list 1..count as p>
                                <#if p-1 == objectList.getNumber()>
                                     <li class="page-item active"><a class="page-link" href="${url}${p-1}" >${p}</a></li>
                                <#else > <li class="page-item"><a class="page-link" href="${url}${p-1}">${p}</a></li>
                                </#if>
                            </#list>

                        <#else>
                        <li class="page-item">
                            <#if objectList.getNumber() !=0>
                                <a class="page-link" href="${url}${objectList.getNumber()-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                    <span class="sr-only">Previous</span>
                                </a>
                        </li>
                            </#if>
                        <#--<#assign count=2>
                        <#list 1..count as p>-->
                            <#if pageNumber == objectList.getNumber()>
                        <li class="page-item active"><a class="page-link" href="${url}${pageNumber}" >${pageNumber+1}</a></li>
                            <#else > <li class="page-item"><a class="page-link" href="${url}${pageNumber}" >${pageNumber+1}</a></li>
                            </#if>

                            <#if pageNumber < objectList.getTotalPages()-1>
                                <#if pageNumber+1 == objectList.getNumber()>
                                <li class="page-item active"><a class="page-link" href="${url}${pageNumber+1}" >${pageNumber+2}</a></li>
                                <#else > <li class="page-item"><a class="page-link" href="${url}${pageNumber+1}" >${pageNumber+2}</a></li>
                                </#if>
                            </#if>

                            <li class="page-item"><a class="page-link" href="" >...</a></li>
                            <li>
                            <#if (objectList.getTotalPages()-1) == objectList.getNumber()>
                                <li class="page-item active"><a class="page-link" href="${url}${objectList.getTotalPages()-1}" >${objectList.getTotalPages()}</a></li>
                            <#else > <li class="page-item"><a class="page-link" href="${url}${objectList.getTotalPages()-1}" >${objectList.getTotalPages()}</a></li>
                            </#if>
                            </li>
                        <li class="page-item">
                            <#if objectList.getNumber() != objectList.getTotalPages()-1>
                                <a class="page-link" href="${url}${objectList.getNumber()+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </#if>
                        </li>
                        </#if>
         </#if>

     </ul>
 </nav>

</#macro>