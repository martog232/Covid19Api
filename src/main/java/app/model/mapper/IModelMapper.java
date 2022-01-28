package app.model.mapper;

public interface IModelMapper<T, U> {
    T toDto(U entity);

    U toEntity(T dto);
}
