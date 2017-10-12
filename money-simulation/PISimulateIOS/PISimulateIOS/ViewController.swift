//
//  ViewController.swift
//  PISimulateIOS
//
//  Created by Jacob Su on 10/12/17.
//  Copyright © 2017 Jacob Su. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var piView : PISimulateView?
    var isAnimating : Bool = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let pi = PISimulateView(frame: self.view.bounds)
        pi.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        pi.backgroundColor = UIColor.white
        self.view.addSubview(pi)
        
        self.piView = pi
        
        startSimulation()
    }
    
    deinit {
        stopSimulation()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    func startSimulation() -> Void {
        isAnimating = true
        
        DispatchQueue.global().async {
            while(self.isAnimating) {
                self.run()
                usleep(20000)
            }
        }
    }

    func stopSimulation() -> Void {
        isAnimating = false
    }
    
    private func run() {
        guard let piView = self.piView else {
            return
        }
        
        DispatchQueue.main.async {
            piView.renderRandomPoint()
        }
    }
}

