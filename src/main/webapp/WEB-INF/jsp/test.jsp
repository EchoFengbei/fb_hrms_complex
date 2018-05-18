<%--
  Created by IntelliJ IDEA.
  User: echo
  Date: 2018/5/14
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<div class="panel-body">
    <nav >
        <ul class="pagination">
            <li><a href="/dept/selectDept?pageNo=1">首页</a></li>
            <c:if test="${curPage==1}">
                <li class="disabled">
                    <a href="#" aria-label="Previous" class="prePage">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${curPage!=1}">
                <li>
                    <a href="#" aria-label="Previous" class="prePage">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${totalPages<5?totalPages:5}" step="1" var="itemPage">
                <c:if test="${curPage == itemPage}">
                    <li class="active"><a href="/dept/selectDept?pageNo=${itemPage}">${itemPage}</a></li>
                </c:if>
                <c:if test="${curPage != itemPage}">
                    <li><a href="/dept/selectDept?pageNo=${itemPage}">${itemPage}</a></li>
                </c:if>
            </c:forEach>

            <c:if test="${curPage==totalPages}">
                <li class="disabled" class="nextPage">
                    <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <c:if test="${curPage!=totalPages}">
                <li>
                    <a href="#" aria-label="Next" class="nextPage">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </c:if>
            <li><a href="/dept/selectDept?pageNo=${totalPages}">尾页</a></li>
        </ul>
    </nav>
</div>