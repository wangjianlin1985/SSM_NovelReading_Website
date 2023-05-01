/**
 * Created by yanghl on 2018/10/6.
 */

/*
 * 根据浏览器访问地址动态设置菜单高亮
 */
$('.sidebar-menu li:not(.treeview) > a').on('click', function(){
    var $parent = $(this).parent().addClass('active');
    $parent.siblings('.treeview.active').find('> a').trigger('click');
    $parent.siblings().removeClass('active').find('li').removeClass('active');
});

$(function () {
    $('.sidebar-menu a').each(function(){
        // console.log(this);
        var cur = window.location.href;
        var url = this.href;
        // console.log(cur.match(url));
        if(cur==url){
            $(this).parent().addClass('active')
                .closest('.treeview-menu').addClass('.menu-open')
                .closest('.treeview').addClass('active');
        }
    });
});