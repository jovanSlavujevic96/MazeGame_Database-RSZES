#include <iostream>
#include <string>
#include <sstream>

#include "keyboard.hpp"
#include "sql.hpp"

int main(int argc, char **argv)
{
    std::unique_ptr<SQL> sql;
    {
        std::string username, password;
        std::cout << "Enter username: ";
        std::cin >> username;
        SetKeyboard(Hidden);
        std::cout << "Enter password: ";
        std::cin >> password;
        std::cout <<'\n';
        SetKeyboard(Normal);
        sql = std::make_unique<SQL>(username.c_str(), password.c_str() );
    }

    sql->print_leaderboard();    

    return 0;
}