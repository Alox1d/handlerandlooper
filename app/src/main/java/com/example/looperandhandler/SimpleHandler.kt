/*
Проблемы (спросить, зачем нужно то или иное улучшение):
1. Не используется java.util.concurrent (AtomicBoolean, ConcurrentLinkedQueue) - спросить, почему нужны;
2. Нет вызова start() при инициализации (в init);
3. Модификаторы доступа у свойств можно улучшить (достатогчно private)
4. (Минор) Метод post не возвращает this -> нет возможности работать с ним как с билдером
5.1 Лучше val task = taskQueue.get(taskQueue.size) -> val task = taskQueue.get(taskQueue.size-1)
5.2 А ещё лучше taskQueue.poll() (в кейсе 5.1 нужно удалить последний элемент)
5.2.1 В случае 5.2 понадобится null-check: например, task?.run()
 */
package com.example.looperandhandler

import java.util.LinkedList

class SimpleHandler : Thread() {

    internal var alive = true
    internal val taskQueue = LinkedList<Runnable>()

    /**
    This method posts the task into the thread
     */
    fun post(task: Runnable) {
        taskQueue.add(task)
    }

    /**
    Stops the handler
     */
    fun quit() {
        alive = false
    }

    override fun run() {
        while (alive) {
            val task = taskQueue.poll()
            task?.run()
        }
    }

}