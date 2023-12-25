package com.example.demowebflax

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootApplication
class DemoWebFlaxApplication

fun main(args: Array<String>) {
    runApplication<DemoWebFlaxApplication>(*args)

//    val just = Mono.just(1)
//            .map {
//                it * 5
//            }.doOnNext {
//                println(it)
//            }.subscribe()
//
//    Flux.just(1, 2, 4).map {
//        it + 2
//    }.map { it / 2 }
//            .doOnNext{ println(
//                    "flux $it"
//            )}.doOnComplete { println("data is out") }
//            .doOnSubscribe{ println("start thread")}
//            .subscribe()


}