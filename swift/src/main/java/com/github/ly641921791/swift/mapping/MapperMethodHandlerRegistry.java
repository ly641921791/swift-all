package com.github.ly641921791.swift.mapping;

import com.github.ly641921791.swift.mapping.support.Count;
import com.github.ly641921791.swift.mapping.support.Delete;
import com.github.ly641921791.swift.mapping.support.DeleteAllById;
import com.github.ly641921791.swift.mapping.support.DeleteByColumn;
import com.github.ly641921791.swift.mapping.support.FindAll;
import com.github.ly641921791.swift.mapping.support.FindAllByColumn;
import com.github.ly641921791.swift.mapping.support.FindAllById;
import com.github.ly641921791.swift.mapping.support.FindAllId;
import com.github.ly641921791.swift.mapping.support.FindById;
import com.github.ly641921791.swift.mapping.support.FindMap;
import com.github.ly641921791.swift.mapping.support.FindMapList;
import com.github.ly641921791.swift.mapping.support.FindOneByColumn;
import com.github.ly641921791.swift.mapping.support.Save;
import com.github.ly641921791.swift.mapping.support.Update;
import com.github.ly641921791.swift.mapping.support.UpdateByColumn;
import com.github.ly641921791.swift.mapping.support.UpdateById;
import com.github.ly641921791.swift.mapping.support.UpdateColumnById;
import com.github.ly641921791.swift.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper方法解析器注册类
 *
 * @author ly
 * @since 1.0.0
 **/
public class MapperMethodHandlerRegistry {

    public Map<String, Class<? extends MapperMethodHandler>> mapperMethodHandlers = new HashMap<>();

    public MapperMethodHandlerRegistry() {
        addMapperMethodHandler(Count.class);
        addMapperMethodHandler(Save.class);
        addMapperMethodHandler(Delete.class);
        addMapperMethodHandler(DeleteAllById.class);
        addMapperMethodHandler(DeleteByColumn.class);
        addMapperMethodHandler(Update.class);
        addMapperMethodHandler(UpdateByColumn.class);
        addMapperMethodHandler(UpdateById.class);
        addMapperMethodHandler(FindAll.class);
        addMapperMethodHandler(FindAllId.class);
        addMapperMethodHandler(FindAllById.class);
        addMapperMethodHandler(FindById.class);
        addMapperMethodHandler(FindMap.class);
        addMapperMethodHandler(FindMapList.class);
        addMapperMethodHandler(FindOneByColumn.class);
        addMapperMethodHandler(FindAllByColumn.class);
        addMapperMethodHandler(UpdateColumnById.class);
    }

    public void addMapperMethodHandler(Class<? extends MapperMethodHandler> mapperMethodHandler) {
        mapperMethodHandlers.put(StringUtils.toLowerCamel(mapperMethodHandler.getSimpleName()), mapperMethodHandler);
    }

    public MapperMethodHandler getMapperMethodResolver(Method method) {
        Class<? extends MapperMethodHandler> mapperMethodHandler = mapperMethodHandlers.get(method.getName());
        if (mapperMethodHandler == null) {
            return null;
        }
        try {
            return mapperMethodHandler.getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            // TODO 没有午餐构造函数
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            // TODO 创建对象失败
        }
        return null;
    }

}
