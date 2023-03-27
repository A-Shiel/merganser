#ifndef METRONOME_H
#define METRONOME_H

#include <iostream>
#include <chrono>
#include <thread>
#include "timer.h"

class Metronome {
  public:
    Metronome(int bpm) : _bpm(bpm), _beat_duration(60.f / bpm), _timer(), _running(false) {}
    
    void start() {

      std::cout << "Metronome started" << "\n";

      _timer.restart();
      _running = true;
      _thread = std::thread(&Metronome::loop, this);
    }

    void stop() {

      std::cout << "Metronome stopped" << "\n";

      _running = false;
      if (_thread.joinable()) {
        _thread.join();
      }
    }

    int getBPM() const {
      return _bpm;
    }

    void setBPM(int bpm) {
      _bpm = bpm;
      _beat_duration = 60.0f / bpm;
    }

    float getElapsedSeconds() {
      return _timer.getElapsedSeconds();
    }

    bool isRunning() {
      return _running;
    }

  private:
    int _bpm;
    float _beat_duration;
    bool _running;
    std::thread _thread;
    Timer _timer;

    void loop() {
      while (_running) {
        std::cout << "click" << "\n";
        std::this_thread::sleep_for(std::chrono::duration<float>(_beat_duration / 2));
        std::cout << "click" << "\n";
        std::this_thread::sleep_for(std::chrono::duration<float>(_beat_duration / 2));
      }
    }
};

#endif // METRONOME_H

