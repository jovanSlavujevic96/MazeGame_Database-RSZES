#ifndef LEADERBOARD_H
#define LEADERBOARD_H

#include <QWidget>
#include <thread>
#include <memory>
#include "sql/include/sql.h"
#include "server/include/server.h"

namespace Ui {
  class Leaderboard;
}

class Leaderboard : public QWidget
{
  Q_OBJECT

public:
  explicit Leaderboard(QWidget *parent = nullptr);
  virtual ~Leaderboard();

  void assign(std::shared_ptr<SQL>& sql);
  void LeaderboardOnScreen(void);

private slots:
  void on_pushButton_clicked();

private:
  Ui::Leaderboard *ui;
  std::shared_ptr<Server> server;
  std::shared_ptr<SQL> sql;

  void interruptTimer(void);
  std::thread timer;
};

#endif // LEADERBOARD_H