package com.buba.boot_phoenix.classroom.mapper;

import com.buba.boot_phoenix.classroom.entity.Classroom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.buba.boot_phoenix.classroom.vo.ClassSaveVO;

import java.util.List;

/**
 * <p>
 * 教室管理 Mapper 接口
 * </p>
 *
 * @author ZL
 * @since 2022-07-12
 */
public interface ClassroomMapper extends BaseMapper<Classroom> {

    /**
     * 功能描述: 修改教室信息
     *
     * @param classSaveVO
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 16:12
     */
    Integer updateClassRoom(ClassSaveVO classSaveVO);

    /**
     * 功能描述: 删除教室信息
     *
     * @param classroomId
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 16:12
     */
    Integer deleteClassRoom(String classroomId);

    /**
     * 功能描述: 删除教室信息
     *
     * @param classroomId
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 16:12
     */
    Integer listDelClassRoom(List<String> classroomId);

    /**
     * 功能描述: 添加-导入教室信息
     *
     * @param classroomList
     * @return  int
     * @throws
     * @Author: zl
     * @Date:   2022/7/14 16:12
     */
    Integer insertListClassRoom(List<Classroom> classroomList);

}
