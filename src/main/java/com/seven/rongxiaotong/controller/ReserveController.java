package com.seven.rongxiaotong.controller;

import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.Reserve;
import com.seven.rongxiaotong.service.ReserveService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource
    private ReserveService reserveService;

    /**
     * 新增预约
     * @author wjh
     * @create 2023/7/3
     *
     * @param reserve 新增预约信息
     * @param bindingResult 检测
     * @return com.seven.rongxiaotong.common.Result<java.lang.String>
     **/
    @PostMapping("/addReserve")
    public Result<String> addReserve(@RequestBody Reserve reserve, BindingResult bindingResult) {
        //检查项目
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "添加失败",s);
        }
        reserveService.addReserve(reserve);
        return new Result(true, StatusCode.ERROR, "添加成功");
    }

    /**
     * 根据id查询
     * @author wjh
     * @create 2023/7/3
     *
     * @param id id
     * @return com.seven.rongxiaotong.common.Result<com.seven.rongxiaotong.entity.Reserve>
     **/
    @GetMapping("/selectReserveById/{id}")
    public Result<Reserve> selectReserveById(@PathVariable("id") Integer id) {
        Reserve reserve = reserveService.selectReserveById(id);
        return new Result<Reserve>(true, StatusCode.OK, "查询成功",reserve);
    }

    /**
     * 根据id更新预约信息
     * @author wjh
     * @create 2023/7/3
     *
     * @param reserve 更新信息
     * @param bindingResult 检测
     * @return com.seven.rongxiaotong.common.Result<java.lang.String>
     **/
    @PutMapping("/updateReserveById")
    public Result<String> updateReserveById(@RequestBody Reserve reserve,BindingResult bindingResult){
        //检查项目
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "修改失败",s);
        }
        reserveService.updateById(reserve);
        return new Result<String>(true, StatusCode.OK, "修改成功");
    }

    /**
     * 根据id删除预约信息
     * @author wjh
     * @create 2023/7/3
     *
     * @param id
     * @return com.seven.rongxiaotong.common.Result
     **/
    @DeleteMapping("/deleteReserveById/{id}")
    public Result deleteReserveById(@PathVariable("id") Integer id){
        Reserve reserve = reserveService.selectReserveById(id);
        if(reserve != null){
            reserveService.removeById(id);
            return new Result(true, StatusCode.OK, "删除成功");
        } else {
            return new Result(false, StatusCode.ERROR, "该预约信息不存在");
        }
    }

    /**
     * 根据用户类型查询预约记录
     * @author wjh
     * @create 2023/7/3
     *
     * @param type 只能在expert/questioner
     * @return com.seven.rongxiaotong.common.Result<java.util.List < com.seven.rongxiaotong.entity.Reserve>>
     **/
    @GetMapping("/selectReserveByNowUser/{type}")
    public Result<List<Reserve>> selectReserveByNowUser(@PathVariable("type") String type) {
        if(!type.equals("questioner") && !type.equals("expert")) {
            return new Result<List<Reserve>>(false, StatusCode.ERROR, "类型只能为expert或questioner");
        }
        List<Reserve> reserves = reserveService.selectReserveByNowUser(type);
        return new Result<List<Reserve>>(true, StatusCode.OK, "查询成功",reserves);
    }
}
