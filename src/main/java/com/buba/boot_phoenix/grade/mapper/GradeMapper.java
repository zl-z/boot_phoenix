package com.buba.boot_phoenix.grade.mapper;

import com.buba.boot_phoenix.grade.dto.GradeFullDTO;
import com.buba.boot_phoenix.grade.dto.StuDTO;
import com.buba.boot_phoenix.grade.entity.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.grade.vo.GradeAddVO;
import com.buba.boot_phoenix.grade.vo.GradePageVO;
import com.buba.boot_phoenix.grade.vo.GradeStuAddVO;
import com.buba.boot_phoenix.grade.vo.StuViewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 班级表 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-07-18
 */
public interface GradeMapper extends BaseMapper<Grade> {

    /**
     * 功能描述: 模糊查-分页 班级信息
     *
     * @param gradePageVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/18 16:56
     */
    List<GradeFullDTO> listGrade(GradePageVO gradePageVO);

    /**
     * 功能描述: 统计班级信息总条数
     *
     * @param gradePageVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/18 19:07
     */
    Integer gradeTotal(GradePageVO gradePageVO);

    /**
     * 功能描述: 更新班级学生数量
     *
     * @param gradeName
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.GradeFullDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/26 16:59
     */
    GradeFullDTO getStuNum(String gradeName);

    /**
     * 功能描述: 编辑班级信息
     *
     * @param gradeAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 10:33
     */
    Integer updateGrade(GradeAddVO gradeAddVO);

    /**
     * 功能描述: 批量删除班级信息
     *
     * @param ids
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/20 14:31
     */
    Integer delGradeInfo(List<String> ids);

    /**
     * 功能描述: 查询班级学生信息
     *
     * @param stuViewVO
     * @return  java.util.List<com.buba.boot_phoenix.grade.dto.StuDTO>
     * @throws
     * @Author: zl
     * @Date:   2022/7/21 16:25
     */
    List<StuDTO> getStuInfo(StuViewVO stuViewVO);

    /**
     * 功能描述: 统计班级学生信息总条数
     *
     * @param stuViewVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/21 18:03
     */
    Integer stuTotal(StuViewVO stuViewVO);

    /**
     * 功能描述: 逻辑删除班级学生信息
     *
     * @param studentId
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/22 16:00
     */
    Integer delClassStu(List<String> studentId);

    /**
     * 功能描述: 修改学生是否入班
     *
     * @param studentId
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/26 10:02
     */
    Integer updateStuTheClass(@Param("bL") Boolean bL, @Param("studentId") String studentId);

    /**
     * 功能描述: 班级学生表添加
     *
     * @param gradeStuAddVO
     * @return  java.lang.Integer
     * @throws
     * @Author: zl
     * @Date:   2022/7/25 21:10
     */
    Integer insertGradeStudent(GradeStuAddVO gradeStuAddVO);

}
