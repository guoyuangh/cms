package cn._2vin.common.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn._2vin.admin.bean.SysModule;
import cn._2vin.admin.bean.SysUser;
import cn._2vin.admin.constant.ConstantAdmin;
import cn._2vin.common.bean.AbstractNumberVO;
import cn._2vin.common.exception.NotRoleRightException;
import cn._2vin.common.util.StringHelper;
public class AdminController extends BaseController {
	/**
	 * 设置导航名称，get传递的时候 ，需要转码
	 * @param vo
	 * @param model
	 * @return
	 * @throws NotRoleRightException 
	 */
	public void setNavTitle(AbstractNumberVO vo,ModelAndView model) throws NotRoleRightException{
		String navT = vo.getNavTitle();
//		if(!StringHelper.isNull(navT)){
//			try {
//			navT = new String(navT.getBytes("ISO8859-1"), "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}else{
			/*就向管理员的sysRole中查找*/
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();
			String  uri = request.getRequestURI();
			Object obj = session.getAttribute(ConstantAdmin.SESSION_ADMIN_INFO);
			System.out.print(obj);
			System.out.print("1111");
			if(obj instanceof SysUser){
				SysUser user = (SysUser)obj;
				List<SysModule> moduleList = user.getSysModules();
				List<SysModule> childrenList = new ArrayList<SysModule>();

				for(SysModule module : moduleList){
					if(null != module.getChildren() && module.getChildren().size() > 0){
						childrenList.addAll(module.getChildren());
					}
				}
				for(SysModule module : childrenList){
					String url = module.getModuleUrl();
					if((uri.length() - url.length()) == uri.indexOf(url)){
						navT = module.getModuleName();
						break;
					}
				}
			}
			/**
			 * 此时还是空的话，原因是无权限，不能访问
			 */
			if(StringHelper.isNull(navT)){
				throw new NotRoleRightException("无操作权限！");
			}
//		}
		vo.setNavTitle(navT);
		model.addObject(NAV_TITLE_KEY,navT);
	}
}
