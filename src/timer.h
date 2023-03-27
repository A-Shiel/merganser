#ifndef TIMER_H
#define TIMER_H

#include <chrono>

class Timer {
  public:
    Timer() {
      _start_time = std::chrono::system_clock::now();
      _current_time = std::chrono::system_clock::now();
      _last_delta_tick = std::chrono::system_clock::now();
      _delta_time = std::chrono::duration<float>(0);
    };
    void restart() {
      _start_time = std::chrono::system_clock::now();
    }
    float getDeltaTime() {
      _current_time = std::chrono::system_clock::now();
      _delta_time = _current_time - _last_delta_tick;
      _last_delta_tick = _current_time;
      return _delta_time.count();
    }
    float getElapsedSeconds() {
      _current_time = std::chrono::system_clock::now();
      std::chrono::duration<float> elapsed_time = _current_time - _start_time;
      return elapsed_time.count();
    }
  private:
    std::chrono::system_clock::time_point _start_time;
    std::chrono::system_clock::time_point _current_time;
    std::chrono::system_clock::time_point _last_delta_tick;
    std::chrono::duration<float> _delta_time;
};

#endif // TIMER_H

