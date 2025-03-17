package jm.task.core.jdbc.model;

import javax.persistence.*;


    @Entity
    /// Аннотация @Entity указывает, что этот класс является JPA-сущностью
    @Table(name = "users")

    public class User {

        @Id                                      //Поле id (Первичный ключ)
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        @Column(nullable = false)                        //// Запрещает NULL в базе данных
        private String name;

        @Column(nullable = false)
        private String lastName;

        @Column(nullable = false)
        private Byte age;

        public User() {                               //Конструктор без параметров нужен для работы JPA (Hibernate использует его для создания объектов).

        }

        public User(String name, String lastName, Byte age) {//Конструктор с параметрами
            this.name = name;
            this.lastName = lastName;
            this.age = age;
        }

        public Long getId() {
            return id;
        }                          // Геттеры и сеттеры позволяют получать и изменять поля объекта

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public Byte getAge() {
            return age;
        }

        public void setAge(Byte age) {
            this.age = age;
        }
    }

