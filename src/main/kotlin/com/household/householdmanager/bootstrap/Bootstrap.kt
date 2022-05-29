package com.household.householdmanager.bootstrap

import com.household.householdmanager.model.ERole
import com.household.householdmanager.model.Product
import com.household.householdmanager.model.ProductType
import com.household.householdmanager.model.Role
import com.household.householdmanager.repository.ProductRepository
import com.household.householdmanager.repository.RoleRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Bootstrap {

    @Bean
    fun databaseInitializer(productRepository: ProductRepository, roleRepository: RoleRepository) = ApplicationRunner {
        val product1 = Product(name = "Apple", quantity = 5, image = "https://thumbs.dreamstime.com/b/red-apple-isolated-clipping-path-19130134.jpg", type = ProductType.FRUIT)
        val product2 = Product(name = "Banana", quantity = 5, image = "https://th-thumbnailer.cdn-si-edu.com/4Nq8HbTKgX6djk07DqHqRsRuFq0=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/d5/24/d5243019-e0fc-4b3c-8cdb-48e22f38bff2/istock-183380744.jpg", type = ProductType.FRUIT)
        val product3 = Product(name = "Tomato", quantity = 10, image = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/Bright_red_tomato_and_cross_section02.jpg/800px-Bright_red_tomato_and_cross_section02.jpg", type = ProductType.VEGETABLE)
        val product4 = Product(name = "Bread", quantity = 1, image = "https://www.thespruceeats.com/thmb/9n3YM4RVKSWyUWQ0JCl7BwHrl1U=/4288x2848/filters:fill(auto,1)/loaf-of-bread-182835505-58a7008c5f9b58a3c91c9a14.jpg", type = ProductType.BAKERY)
        val product5 = Product(name = "Beer", quantity = 6, image = "https://toppng.com/uploads/preview/alcohol-bottles-png-vector-transparent-stock-green-beer-bottle-11562975842xjc8a0x30c.png", type = ProductType.ALCOHOL)
        val product6 = Product(name = "Butter", quantity = 2, image = "https://www.picng.com/upload/butter/png_butter_10866.png", type = ProductType.DAIRY)
        val product7 = Product(name = "Coke", quantity = 4, image = "https://pngroyale.com/wp-content/uploads/2021/11/de-coca-cola-png.png", type = ProductType.DRINK)
        val product8 = Product(name = "Milk", quantity = 12, image = "https://toppng.com/uploads/preview/milk-png-11554019731zzc03ngzzv.png", type = ProductType.DAIRY)
        val product9 = Product(name = "Almond Milk", quantity = 6, image = "https://img.favpng.com/0/20/3/almond-milk-plant-milk-milk-substitute-cream-png-favpng-cs4ZD1Z0vTZATm8HD0sUcLmxf.jpg", type = ProductType.VEGAN)
        val product10 = Product(name = "Tortilla", quantity = 3, image = "https://www.nicepng.com/png/detail/985-9851773_tortilla-wrap.png", type = ProductType.BAKERY)
        val product11 = Product(name = "Flour", quantity = 1, image = "https://toppng.com/uploads/preview/flour-11528321205livs6yiw7u.png", type = ProductType.BAKERY)
        val product12 = Product(name = "Sugar", quantity = 1, image = "https://toppng.com/uploads/preview/sugar-png-115539715091pkz9bvunf.png", type = ProductType.CONDIMENT)
        val product13 = Product(name = "Pepper", quantity = 1, image = "https://www.seekpng.com/png/full/69-697541_chili-pepper-png.png", type = ProductType.VEGETABLE)
        val product14 = Product(name = "Chicken breast", quantity = 4, image = "https://www.pngkey.com/png/full/120-1207993_chicken-breast-fillets-chicken-breast-cuts.png", type = ProductType.MEAT)
        val product15 = Product(name = "Beef", quantity = 2, image = "https://image.similarpng.com/very-thumbnail/2020/09/Raw-beef-meat-pieces-on-transparent-background-PNG.png", type = ProductType.MEAT)

        val products = listOf(
            product1,
            product2,
            product3,
            product4,
            product5,
            product6,
            product7,
            product8,
            product9,
            product10,
            product11,
            product12,
            product13,
            product14,
            product15
        )

        products.forEach { productRepository.save(it) }

        val userRole = Role(name = ERole.USER)
        val adminRole = Role(name = ERole.ADMIN)

        roleRepository.save(userRole)
        roleRepository.save(adminRole)
    }
}