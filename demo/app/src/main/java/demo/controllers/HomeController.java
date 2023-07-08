package demo.controllers;

import demo.views.HomeView;

public class HomeController {
        private HomeView home;

        public HomeController(HomeView homeView) {
                this.home = homeView;
        }
}
