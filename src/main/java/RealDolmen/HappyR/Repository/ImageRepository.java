package RealDolmen.HappyR.Repository;

import RealDolmen.HappyR.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for Image entities, providing CRUD operations.
 */
    @Repository
    public interface ImageRepository extends JpaRepository<Image, Long> {

}
