//
//  ViewController.swift
//  money-simulation-ios
//
//  Created by Jacob Su on 10/11/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var moneyView : MoneySimulationView?
    var moneys : [Int] = []
    let N = 50
    var isAnimating = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        let moneyView = MoneySimulationView()
        moneyView.frame = self.view.bounds
        moneyView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.view.addSubview(moneyView)
        
        self.moneyView = moneyView
        
        for _ in 0..<N {
            moneys.append(50)
        }
        
        startSimulation()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    deinit {
        stopSimulation()
    }

    func startSimulation() -> Void {
        isAnimating = true
        
        DispatchQueue.global().async {
            while (self.isAnimating) {
                self.run()
                usleep(40000)
            }
        }
    }
    
    func stopSimulation() -> Void {
        isAnimating = false
    }
    
    func run() -> Void {
        guard let moneyView = self.moneyView else {
            return
        }
        
        DispatchQueue.main.async {
            moneyView.render(moneys: self.moneys)
        }
        
        for _ in 0...20 {
            for (i, _) in moneys.enumerated() {
                if (moneys[i] > 0) {
                    let j = Int(arc4random_uniform(UInt32(moneys.count)))
                    if (i != j) {
                        moneys[i] -= 1
                        moneys[j] += 1
                    }
                }
            }
        }
    }
}

