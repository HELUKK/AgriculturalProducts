package com.seven.rongxiaotong.controller;

import com.github.pagehelper.PageInfo;
import com.seven.rongxiaotong.common.Result;
import com.seven.rongxiaotong.common.StatusCode;
import com.seven.rongxiaotong.entity.Expert;
import com.seven.rongxiaotong.entity.Question;
import com.seven.rongxiaotong.service.ExpertService;
import com.seven.rongxiaotong.service.QuestionService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 问题模块接口
 * @author wjh
 * @create 2023/6/30
 **/
//跨域
@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private QuestionService questionService;

    //根据id查询询问情况
    @GetMapping("/selectId/{id}")
    public Result<Question> selectById(@PathVariable("id") Integer id){
        Question question = questionService.selectById(id);
        return new Result(true, StatusCode.OK,"查询成功",question);
    }

    //添加询问情报
    @PostMapping("/add")
    public Result add(@RequestBody Question question, BindingResult bindingResult) {
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
        question.setStatus(0);//提问态转换为0--提问中
        questionService.insert(question);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @author wjh
     * @create 2023/7/1
     **/
    //以标题为keys模糊查询第pageNum的数据,每页30行
    @GetMapping("/findPageQues/{keys}/{pageNum}")
    public Result<PageInfo<Question>> findPageQues(@PathVariable("keys") String keys, @PathVariable("pageNum") Integer pageNum) {
        PageInfo<Question> questionPageInfo = questionService.selectByKeys(keys,pageNum);
        return new Result<>(true,StatusCode.OK,"查询成功",questionPageInfo);
    }

    /**
     * 查询所有问答结果
     * @author wjh
     * @create 2023/7/1
     **/
    @GetMapping("/findAllQues/{pageNum}")
    public Result<PageInfo<Question>> findAllQues(@PathVariable Integer pageNum) {
        PageInfo<Question>  questionPageInfo = questionService.selectByKeys(null,pageNum);
        return new Result(true, StatusCode.OK, "查询成功", questionPageInfo);
    }

    /**
     * 查询所有专家
     * @author wjh
     * @create 2023/7/1
     **/
    @GetMapping("/findAllExpert/{pageNum}")
    public Result<PageInfo<Expert>> findAllExpert(@PathVariable Integer pageNum) {
        PageInfo<Expert> expertPageInfo = questionService.selectExpert(pageNum);
        return new Result(true, StatusCode.OK, "查询成功", expertPageInfo);
    }

    @GetMapping("/findExpert/{keys}/{pageNum}")
    public Result<PageInfo<Expert>> findExpert(@PathVariable("keys") String keys, @PathVariable("pageNum") Integer pageNum) {
        PageInfo<Expert> expertPageInfo = questionService.selectExpertByKeys(keys,pageNum);
        return new Result(true, StatusCode.OK, "查询成功", expertPageInfo);
    }
}
