package cn.nicecoder.newssys.controller;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.nicecoder.newssys.common.enums.TypeEnum;
import cn.nicecoder.newssys.domain.entity.base.SysMenu;
import cn.nicecoder.newssys.domain.entity.base.SysRole;
import cn.nicecoder.newssys.domain.entity.base.SysRoleMenu;
import cn.nicecoder.newssys.domain.entity.base.SysUser;
import cn.nicecoder.newssys.domain.entity.biz.News;
import cn.nicecoder.newssys.domain.request.biz.NewsCommentDO;
import cn.nicecoder.newssys.domain.request.biz.NewsDO;
import cn.nicecoder.newssys.domain.response.base.MenuNodeVO;
import cn.nicecoder.newssys.domain.response.base.SysUserVO;
import cn.nicecoder.newssys.domain.response.biz.NewsCatalogVO;
import cn.nicecoder.newssys.domain.response.biz.NewsCommentVO;
import cn.nicecoder.newssys.domain.response.biz.NewsPageVO;
import cn.nicecoder.newssys.domain.response.biz.NewsVO;
import cn.nicecoder.newssys.domain.comm.MenuTreeResp;
import cn.nicecoder.newssys.service.base.SysMenuService;
import cn.nicecoder.newssys.service.base.SysRoleMenuService;
import cn.nicecoder.newssys.service.base.SysRoleService;
import cn.nicecoder.newssys.service.base.SysUserService;
import cn.nicecoder.newssys.service.biz.NewsCatalogService;
import cn.nicecoder.newssys.service.biz.NewsCommentService;
import cn.nicecoder.newssys.service.biz.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 页面跳转
 * @author: xxxxx
 * @date: 2020/12/22 下午2:24
 */
@Controller
public class PageController {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsCatalogService newsCatalogService;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    NewsCommentService newsCommentService;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @GetMapping("/")
    public String index(Model model, NewsDO newsDO){
        // 查询数据
        List<NewsVO> newsList =  newsService.listPageNews(newsDO);
        List<NewsVO> newsWatchingTopList =  newsService.listNewsWatchingTopList();
        List<NewsVO> newsClickTopList =  newsService.listNewsClickTopList();
        List<NewsCatalogVO> catalogVOList = newsCatalogService.listAllCatalog();
        // 封装数据
        NewsPageVO newsPageVO = new NewsPageVO(newsList, newsDO.getCurrent(), newsDO.getSize(), newsDO.getPages());
        model.addAttribute("current", newsDO.getCurrent());
        model.addAttribute("size", newsDO.getSize());
        model.addAttribute("pages", newsDO.getPages());
        model.addAttribute("pageResult", newsPageVO);
        model.addAttribute("newsWatchingTop",newsWatchingTopList);
        model.addAttribute("newsClickTop",newsClickTopList);
        model.addAttribute("catalogResult", catalogVOList);
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        List<MenuNodeVO> menuTreeRoot = sysMenuService.createMenuTreeRoot(false, true);
        MenuTreeResp resp = new MenuTreeResp(menuTreeRoot);
        model.addAttribute("menuTree", resp);
        String firstHref = getFirstHref(menuTreeRoot.get(0));
        model.addAttribute("firstHref", firstHref);
        return "admin/index";
    }
    public String getFirstHref(MenuNodeVO nodeVO){
        List<MenuNodeVO> children = nodeVO.getChildren();
        for(MenuNodeVO menuNodeVO : children){
            if(StrUtil.isNotEmpty(menuNodeVO.getHref())){
                return menuNodeVO.getHref();
            }else{
                return getFirstHref(menuNodeVO);
            }
        }
        return "";
    }

    @GetMapping("/homepage")
    public String homepage(Model model){
        return "admin/home/homepage";
    }

    @GetMapping("/console")
    public String console(){
        return "admin/home/console";
    }

    @GetMapping("/403")
    public String accessdenied(){
        return "403";
    }

    @GetMapping("/user/role")
    public String userRole(){
        return "admin/user/role";
    }

    @GetMapping("/user/list")
    public String userList(){
        return "admin/user/user";
    }

    @GetMapping("/menu/list")
    public String menuList(Model model){
        return "admin/menu/menu";
    }

    @GetMapping("/user/info")
    public String userInfo(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        SysUserVO sysUserVO = sysUserService.getOneByUsername(username);
        model.addAttribute("barberUserVO",sysUserVO);
        return "admin/user/info";
    }

    @GetMapping("/user/roleform")
    public String roleform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysRole SysRole = new SysRole();
        if(id != null) {
            SysRole = sysRoleService.getById(id);
        }
        List<SysRoleMenu> barberRoleMenuList = sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>()
                .eq(SysRoleMenu::getRoleId, id));
        StringBuffer menuBuffer = new StringBuffer();
        barberRoleMenuList.stream().forEach(item ->{
            menuBuffer.append(item.getMenuId()).append(",");
        });
        if(menuBuffer.length() > 0){menuBuffer.deleteCharAt(menuBuffer.length() - 1);}
        model.addAttribute("menus",menuBuffer.toString());
        model.addAttribute("barberRole", SysRole);
        return "admin/user/roleform";
    }

    @GetMapping("/menu/menuform")
    public String menuform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysMenu sysMenu = new SysMenu();
        if(id != null) {
            sysMenu = sysMenuService.getById(id);
        }
        model.addAttribute("barberMenu", sysMenu);
        return "admin/menu/menuform";
    }

    @GetMapping("/user/userform")
    public String userform(Model model, @RequestParam(value = "id", required = false) Long id){
        SysUser sysUser = new SysUser();
        if(id != null) {
            sysUser = sysUserService.getById(id);
        }
        model.addAttribute("barberUser", sysUser);
        List<SysRole> roleList = sysRoleService.getRoleByUsername(sysUser.getUsername());
        Long[] roleArr = new Long[roleList.size()];
        for(SysRole item : roleList){
            roleArr[roleList.indexOf(item)] = item.getId();
        }
        model.addAttribute("roles", roleArr);
        return "admin/user/userform";
    }
    
    @GetMapping("/news/{id}")
    public String detailPage(Model model, @PathVariable("id") Integer id){
        News current = newsService.getOne(new LambdaQueryWrapper<News>().eq(News::getId, id));
        News newsPre = newsService.getOne(new LambdaQueryWrapper<News>().lt(News::getId, id).last("limit 1"));
        News newsNext = newsService.getOne(new LambdaQueryWrapper<News>().gt(News::getId, id).last("limit 1"));
        NewsVO newsVO = new NewsVO();
        newsVO.setId(current.getId());
        newsVO.setTitle(current.getTitle());
        newsVO.setPublisher(current.getAuthor());
        newsVO.setWatching(0);
        newsVO.setContent(current.getContent());
        newsVO.setPublishTime(DateUtil.format(current.getCreateTime(), DatePattern.NORM_DATETIME_PATTERN));
        newsVO.setClick(0);

        // 评论列表
        NewsCommentDO queryParam = new NewsCommentDO();
        queryParam.setType(TypeEnum.COMMENT_TO_NEWS.getCode());
        queryParam.setCommTo(current.getId());
        List<NewsCommentVO> newsCommentVOList = newsCommentService.listPageComment(queryParam);

        // 返回数据
        model.addAttribute("newsCur", newsVO);
        model.addAttribute("newsPre", newsPre);
        model.addAttribute("newsNext", newsNext);
        model.addAttribute("commentList", newsCommentVOList);
        return "newsPage";
    }

    @GetMapping("/member/list")
    public String memberList(){
        return "admin/member/member";
    }

    @GetMapping("/order/list")
    public String orderList(){
        return "admin/order/order";
    }

    @GetMapping("/user/password")
    public String userPassword(){
        return "admin/user/password";
    }

}
