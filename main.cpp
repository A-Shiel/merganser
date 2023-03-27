#include <iostream>
#include <thread>
#include "timer.h"
#include "metronome.h"

int main() {  

  Timer t;
  Metronome m(120);

  m.start();

  std::this_thread::sleep_for(std::chrono::seconds(5));
  m.setBPM(60);
  std::this_thread::sleep_for(std::chrono::seconds(5));

  m.stop();

  float es = m.getElapsedSeconds();
  std::cout << "Elapsed seconds " << es << "\n";

  return 0;
}

